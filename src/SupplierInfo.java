import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;

public class SupplierInfo {

    public static void main(String[] args) {
        String url="jdbc:mysql://localhost:3306/cdacdb";
        String sql="{call  fetch_SupplierInfo(?)}";


        try {
        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        Connection conn = DriverManager.getConnection(url, "root", "password");

        CallableStatement stmt = conn.prepareCall(sql);
        stmt.setInt(1,2001);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()){
            System.out.println("Supplier ID: " + rs.getInt(1) + ", Supplier name: " + rs.getString(2) + ", Supplier Address: " + rs.getString(3) + "rating :" + rs.getInt(4) + "Commision: "+rs.getInt(5));
        }

    } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

}