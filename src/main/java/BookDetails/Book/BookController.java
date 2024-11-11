package BookDetails.Book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getBookDetails() {
        return bookService.getBookDetails();
    }

    @PostMapping("/addNewBook")
    public void addNewBook(@RequestBody BookDTO bookDTO) {
        bookService.addNewBook(bookDTO);
    }

    @GetMapping("/getBookByIsbn/{isbn}")
    public Optional<Book> getBookByIsbn(@PathVariable String isbn) {
        return bookService.getBookDetailsByIsbn(isbn);
    }

    @GetMapping("/getBooksByAuthorId/{authorId}")
    public List<Book> getBooksByAuthorId(@PathVariable Long authorId) {
        return bookService.getBookListByAuthor(authorId);
    }
}
