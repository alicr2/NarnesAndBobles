import org.example.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByGenre(String genre);
    List<Book> findByPublisher(String publisher);
    List<Book> findTop10ByOrderByCopiesSoldDesc();
    List<Book> findByRatingGreaterThanEqual(double rating);
}
