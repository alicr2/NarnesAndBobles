import org.example.model.Book;
import org.example.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/genre/{genre}")
    public ResponseEntity<List<Book>> getBooksByGenre(@PathVariable String genre) {
        List<Book> books = bookService.getBooksByGenre(genre);

        if (books == null || books.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/top-sellers")
    public ResponseEntity<List<Book>> getTopSellers() {
        List<Book> books = bookService.getTopSellers();

        if (books == null || books.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/rating/{rating}")
    public ResponseEntity<List<Book>> getBooksByRating(@PathVariable double rating) {
        if (rating < 0 || rating > 5) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        List<Book> books = bookService.getBooksByRating(rating);

        if (books == null || books.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @PutMapping("/discount")
    public ResponseEntity<Void> applyDiscount(
            @RequestParam String publisher,
            @RequestParam double discountPercent) {

        if (discountPercent <= 0 || discountPercent > 100) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        bookService.applyDiscount(publisher, discountPercent);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
