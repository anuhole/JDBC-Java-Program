import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

public class TestBookManager {
    public static void main(String[] args) {
        BookManager dao = new BookDoa();
        try {
            String url = "jdbc:mysql://localhost:3306/cdacdb";
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            Connection conn = DriverManager.getConnection(url, "root", "password");


            dao.addBook(new Book(101,"Bhura","Sharad",100,"Read"));
            dao.addBook(new Book(102,"OOPs Java","Tanvi",300,"Java"));
            dao.addBook(new Book(103,"Python programming","Shivam",500,"Python"));
            dao.addBook(new Book(104," programming","Shivam",400,"Python"));

            System.out.println("All Book are: ");
            for(Book b:dao.displayAllBooks()){
                System.out.println(b);
            }




            List<Book> list=dao.displayAllBooks();
            for (Book b: list){
                System.out.println(b);
            }

            System.out.println("get by id");
            System.out.println( dao.getBookWithID(101));
            System.out.println("Deleting Book");
            dao.deleteBook(104);
            System.out.println(" After delete All Book are: ");
            for(Book b:dao.displayAllBooks()){
                System.out.println(b);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
