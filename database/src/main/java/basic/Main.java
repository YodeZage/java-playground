package basic;

public class Main {

    public static void main(String[] args) {

        String JDBC_URL = "jdbc:mysql://localhost:3306/java_playground";
        String JDBC_USER = "java-playground-admin";
        String JDBC_PASSWORD = "P@ssword";

        JdbcService service = new JdbcService(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
        service.runMethods();

    }
}
