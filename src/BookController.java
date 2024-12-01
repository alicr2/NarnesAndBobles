import org.example.model.Book;

import org.example.repository.BookRepository;
import org.example.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@CrossOrigin(origins = "*") // Allow all origins during testing
@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    private BookRepository bookRepository;

    public BookController(BookService bookService, BookRepository bookRepository) {
        this.bookService = bookService;
        this.bookRepository = bookRepository;
    }

    // Get books by genre
    @GetMapping("/booksgenre/{genre}")
    public ResponseEntity<List<Book>> getBooksByGenre(@PathVariable String genre) {
        List<Book> books = bookService.getBooksByGenre(genre);
        if (books == null || books.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    // Get top 10 best-sellers
    @GetMapping("/top-sellers")
    public ResponseEntity<List<Book>> getTopSellers() {
        List<Book> books = bookService.getTopSellers();
        if (books == null || books.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    // Get books by rating
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


    @RequestMapping(value = "/publisher/{publisher}/discountPercentage/{discountPercentage}", method = {RequestMethod.PUT})
    public ResponseEntity<Void> applyDiscountToPublisherBooks(
            @PathVariable String publisher,
            @PathVariable double discountPercentage) {
        if (discountPercentage <= 0 || discountPercentage > 100) {
            throw new IllegalArgumentException("Discount percentage must be between 0 and 100");
        }

        bookRepository.findByPublisher(publisher).forEach(book -> {
            double discountedPrice = book.getPrice() * (1 - discountPercentage / 100);
            book.setPrice(discountedPrice);
            bookRepository.save(book);
        });

        return ResponseEntity.noContent().build(); // Return HTTP 204 No Content
    }
}