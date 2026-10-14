import java.sql.*;
import java.util.Scanner;

public class Validation {

    public static boolean validateUser(String username,String password){
        String url="jdbc:mysql://localhost:3306/cdacdb";
        String sql="select * from user where username=? && passwords=?";
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            Connection conn = DriverManager.getConnection(url, "root", "password");

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,username);
            stmt.setString(2,password);

            try( ResultSet rs = stmt.executeQuery())
            {
                return rs.next();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        System.out.println("Enter the usename");
        String username=sc.nextLine();
        System.out.println("Enter the password");
        String password=sc.nextLine();

        if(validateUser(username,password)){
            System.out.println("Successfully Login");
        }
        else
            System.out.println("Invalid Crediantial");



    }
}
