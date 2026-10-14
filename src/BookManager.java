import java.util.List;

public interface BookManager {
    void addBook(Book book) throws BookAlreadyExistsException;
    void deleteBook(int bookId) throws BookNotFoundException;
    public List<Book> displayAllBooks();
    public Book getBookWithID(int bookId)throws BookNotFoundException;
    List<Book> getBAuthor(String author);
    void incrPrice(int bookId, int amount) throws BookNotFoundException;


}
