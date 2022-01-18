package basic;

public class Main {
    public static void main(String[] args) {
        try {
            HttpWebServer httpWebServer = new HttpWebServer();
            httpWebServer.startWebServer();
        } catch (Exception e) {
            System.out.println("!!!!!!!System ERROR!!!!!!! " + e);
        }
    }
}
