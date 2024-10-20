package books;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
//import book.ratings.project.model.Book;
import books.Book;


import java.util.List;

@Repository
public class BookRepository {

    private final JdbcTemplate jdbcTemplate;

    // Constructor to initialize JdbcTemplate
    public BookRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Method using JdbcTemplate to find books by genre
    public List<Book> findBooksByGenre(String genre) {
        String sql = "SELECT * FROM books WHERE genre = ?";
        return jdbcTemplate.query(sql, new Object[]{genre}, (rs, rowNum) -> {
            Book book = new Book();
            book.setId(rs.getLong("id"));
            book.setTitle(rs.getString("title"));
            book.setAuthor(rs.getString("author"));
            book.setYearPublished(rs.getInt("year_published"));
            book.setGenre(rs.getString("genre"));
            book.setPublisher(rs.getString("publisher"));
            book.setRatings(rs.getDouble("ratings"));
            return book;
        });
    }

    // Inner interface that extends JpaRepository for JPA methods
    public interface JpaBookRepository extends JpaRepository<Book, Long> {
        List<Book> findByRatingGreaterThanEqual(double rating);
    }
}
