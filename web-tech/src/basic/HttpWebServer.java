package basic;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class HttpWebServer extends Thread {
    private Socket socket;

    public void startWebServer() throws IOException {
        // set listen port
        ServerSocket serverSocket = new ServerSocket(8080);
        System.out.println("Server is running .......");
        while (true) {
            socket = serverSocket.accept();
            System.out.println("Connected from " + socket.getRemoteSocketAddress());
            run();
        }
    }

    public void run() {
        try (InputStream inputStream = this.socket.getInputStream()) {
            try (OutputStream outputStream = this.socket.getOutputStream()) {
                handle(inputStream, outputStream);
            }
        } catch (Exception e) {
            System.out.println("Input error maybe client disconnected." + e);
            try {
                this.socket.close();
            } catch (IOException ioe) {
                System.out.println("We can't close the socket." + ioe);
            }
        }
    }

    private void handle(InputStream input, OutputStream output) throws IOException {
        System.out.println("Process new http request......");
        var reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));
        var writer = new BufferedWriter(new OutputStreamWriter(output, StandardCharsets.UTF_8));

        // read HTTP request
        boolean requestOK = false;
        String first = reader.readLine();
        // only support HTTP/1.x for GET method with path /
        if (first.startsWith("GET / HTTP/1.")) {
            requestOK = true;
        }

        System.out.println("=======HTTP header start=======");
        while (true) {
            String header = reader.readLine();
            // when the empty line reads in, that means we have 2 \r\n and the header is done
            if (header.isEmpty()) {
                break;
            }
            System.out.println(header);
        }
        System.out.println("=======HTTP header end=======");

        System.out.println(requestOK ? "Success" : "Error");
        if (!requestOK) {
            // response error
            writer.write("HTTP/1.0 404 Not Found\r\n");
            writer.write("Content-Length: 0\r\n");
            writer.write("\r\n");
            writer.flush();
        } else {
            // response success
            String data = "<html><body><h1>Hello World!</h1></body></html>";
            int length = data.getBytes(StandardCharsets.UTF_8).length;
            writer.write("HTTP/1.0 200 OK\r\n");
            writer.write("Connection: close\r\n");
            writer.write("Content-Type: text/html\r\n");
            writer.write("Content-Length: " + length + "\r\n");
            writer.write("\r\n");
            writer.write(data);
            writer.flush();
        }
    }

}
