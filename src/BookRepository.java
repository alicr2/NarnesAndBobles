package geekText;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class BookRepository {

    private final JdbcTemplate jdbcTemplate;

    public BookRepository(JdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> findBooksByGenre(String genre) {
        String sql = "SELECT * FROM books WHERE genre = ?";
        return jdbcTemplate.query(sql, new Object[]{genre}, (rs, rowNum) -> {
            Book book = new Book();
            book.setId(rs.getInt("id"));
            book.setTitle(rs.getString("title"));
            book.setAuthor(rs.getString("author"));
            book.setYearPublished(rs.getInt("year_published"));
            book.setGenre(rs.getString("genre"));
            book.setPublisher(rs.getString("publisher"));
            book.setRatings(rs.getDouble("ratings"));
            return book;
        });
    }
}
