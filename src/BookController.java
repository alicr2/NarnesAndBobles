package geekText;

import com.example.books.entity.Book;
import com.example.books.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    // Retrieve Top 10 Best Sellers (Books with the most copies sold)
    @GetMapping("/top-sellers")
    public List<Book> getTopSellers() {
        return bookRepository.findTop10ByOrderByCopiesSoldDesc();

        public List<Book> getBooksByRating(@RequestParam double rating) {
            return bookRepository.findByRatingGreaterThanEqual(rating);
    }
}
