package basic;

import java.sql.*;

public class JdbcService {

    private String JDBC_URL;
    private String JDBC_USER;
    private String JDBC_PASSWORD;

    public JdbcService(String JdbcUrl, String JdbcUser, String JdbcPassword) {
        this.JDBC_URL = JdbcUrl;
        this.JDBC_USER = JdbcUser;
        this.JDBC_PASSWORD = JdbcPassword;
    }

    public void runMethods() {
//        BasicRun();
        PreparedStatementRun();
    }

    public void PreparedStatementRun() {
        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            try (PreparedStatement ps = conn.prepareStatement("SELECT id, first_name, last_name, email FROM student WHERE gender=? AND id=?")) {
                ps.setObject(1, "male");
                ps.setObject(2, 1);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        int id = rs.getInt(1);
                        String first_name = rs.getString(2);
                        String last_name = rs.getString(3);
                        String email = rs.getString(4);

                        System.out.println("============================");
                        System.out.println(id + " " + first_name + " " + last_name + " " + email);
                        System.out.println("============================");
                    }
                }
            }
        } catch (SQLException sqlException) {
            System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxx");
            System.out.println(sqlException);
            System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        }
    }

    public void BasicRun() {
        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            try (Statement stmt = conn.createStatement()) {
                try (ResultSet rs = stmt.executeQuery("SELECT id, first_name, last_name, email FROM student WHERE gender='male'")) {
                    while (rs.next()) {
                        int id = rs.getInt(1);
                        String first_name = rs.getString(2);
                        String last_name = rs.getString(3);
                        String email = rs.getString(4);

                        System.out.println("============================");
                        System.out.println(id + " " + first_name + " " + last_name + " " + email);
                        System.out.println("============================");
                    }
                }
            }
        } catch (SQLException sqlException) {
            System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxx");
            System.out.println(sqlException);
            System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        }
    }

}
