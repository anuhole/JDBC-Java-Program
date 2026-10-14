import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDoa implements BookManager {
    private Connection conn=null;

    // Constructor to pass active DB connection
    public BookDoa() {
        conn =DButils.getConnection();
    }

    @Override
    public void addBook(Book book) throws BookAlreadyExistsException {
        String sql = "INSERT INTO Book (bookId, title, author, price) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, book.getBookId());
            stmt.setString(2, book.getTitle());
            stmt.setString(3, book.getAuthor());
            stmt.setFloat(4, book.getPrice());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Successfully added!");
            }
        } catch (SQLException e) {
            // Check for primary key constraint violation code if applicable
            e.printStackTrace();
        }
    }

    @Override
    public void deleteBook(int bookId) throws BookNotFoundException {
        String sql = "DELETE FROM Book WHERE bookId = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, bookId);
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected == 0) {
                throw new BookNotFoundException("Book with ID " + bookId + " not found.");
            }
            System.out.println("Successfully deleted!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Book> displayAllBooks() {
        String sql = "SELECT * FROM Book";
        List<Book> newList = new ArrayList<>();

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                // Ensure constructor argument count matches your Book class definition
                Book b = new Book(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5));
                newList.add(b);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newList;
    }

    @Override
    public Book getBookWithID(int bookId) throws BookNotFoundException {
        Book b = null;
        String sql = "SELECT * FROM Book WHERE bookId = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, bookId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    b = new Book(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5));
                } else {
                    throw new BookNotFoundException("Book with ID " + bookId + " not found.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return b;
    }

    @Override
    public List<Book> getBAuthor(String author) {
        List<Book> newList = new ArrayList<>();
        String sql = "SELECT * FROM Book WHERE author = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, author);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Book b = new Book(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5));
                    newList.add(b);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newList;
    }

    @Override
    public void incrPrice(int bookId, int amount) throws BookNotFoundException {
        String sql = "UPDATE Book SET price = price + ? WHERE bookId = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, amount); // First parameter is 'amount'
            stmt.setInt(2, bookId); // Second parameter is 'bookId'

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Update Successfully");
            } else {
                throw new BookNotFoundException("Book with ID " + bookId + " not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}