import java.sql.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        String url="jdbc:mysql://localhost:3306/cdacdb";
       // String sql1="select * from dept";
        String sql="insert into dept values(80, 'HR', 'Mumbai')";
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            //class.forName(com.my.cj.jdbc.Driver)


            Connection conn = DriverManager.getConnection(url, "root", "password");
           // System.out.println(conn);

           Statement stmt = conn.createStatement();

          //ResultSet rs =  stmt.executeQuery(sql1);
           int i= stmt.executeUpdate(sql);
           if(i>0)
                 System.out.println("Successfully insert");




//           while (rs.next()){
//               System.out.print(rs.getInt("deptno") + " : " + rs.getString("dname") + " : " +rs.getString("loc"));
//               System.out.println();
//           }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


    }
    }
