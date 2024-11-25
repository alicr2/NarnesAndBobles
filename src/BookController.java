import org.example.model.Book;
import org.example.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*") // Allow all origins during testing
@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/api/booksgenre/{genre}")
    public ResponseEntity<List<Book>> getBooksByGenre(@PathVariable String genre) {
        List<Book> books = bookService.getBooksByGenre(genre);
        if (books == null || books.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/api/books/top-sellers")
    public ResponseEntity<List<Book>> getTopSellers() {
        List<Book> books = bookService.getTopSellers();
        if (books == null || books.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/api/books/rating/{rating}")
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

    @PutMapping ("/api/books/publisher/discount/{publisher}/{discountPercent}")
    public ResponseEntity<Void> applyDiscount(
            @PathVariable String publisher,
            @PathVariable double discountPercent) {
        System.out.println("Method: PATCH, Publisher: " + publisher + ", Discount: " + discountPercent);

        if (discountPercent <= 0 || discountPercent > 100) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        bookService.applyDiscount(publisher, discountPercent);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}