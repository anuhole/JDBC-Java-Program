import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DButils {

    private static final String URL = "jdbc:mysql://localhost:3306/cdacdb";
    private static final String USER = "root";
    private static final String PASSWORD = "password"; // Replace with your actual password

    public static Connection getConnection() {
        Connection conn = null;
        try {
            // Registering driver is optional in JDBC 4.0+, but safe
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());

            // Assign directly to the local connection variable to return
            conn = DriverManager.getConnection(URL, USER, PASSWORD);

        } catch (SQLException e) {
            System.err.println("Database Connection Error: " + e.getMessage());
            e.printStackTrace();
        }
        return conn;
    }

    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}


