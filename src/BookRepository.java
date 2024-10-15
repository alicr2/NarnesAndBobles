import geekText.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookRepository {

    private final JdbcTemplate jdbcTemplate;

    public BookRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

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

    public interface JpaBookRepository extends JpaRepository<Book, Long> {
        List<Book> findByRatingGreaterThanEqual(double rating);
    }
}
