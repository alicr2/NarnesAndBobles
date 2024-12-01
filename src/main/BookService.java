import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.example.model.Book;
import org.example.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @PersistenceContext
    private EntityManager entityManager;

    // Fetch books by genre
    public List<Book> getBooksByGenre(String genre) {
        return bookRepository.findByGenre(genre);
    }

    // Fetch top 10 best-sellers
    public List<Book> getTopSellers() {
        return bookRepository.findTop10ByOrderByCopiesSoldDesc();
    }

    // Fetch books by rating
    public List<Book> getBooksByRating(double rating) {
        return bookRepository.findByRatingGreaterThanEqual(rating);
    }
}
