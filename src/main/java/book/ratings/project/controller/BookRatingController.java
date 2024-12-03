package book.ratings.project.controller;

import book.ratings.project.dto.RatingDto;
import book.ratings.project.dto.CommentDto;
import book.ratings.project.model.Book;
import book.ratings.project.service.BookService;
import book.ratings.project.service.CommentService;
import book.ratings.project.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.OptionalLong;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/books")
public class BookRatingController {

    @Autowired
    private BookService bookService;

    @Autowired
    private RatingService ratingService;

    @Autowired
    private CommentService commentService;



    // Rate a book
    @PostMapping("/{bookId}/rate")
    public ResponseEntity<?> rateBookByUserID(@PathVariable Long bookId, @RequestBody RatingDto ratingDto) {
        if(ratingDto.getRating()>5 || ratingDto.getRating() < 1) return ResponseEntity.badRequest().body("Rating must be between 1 and 5.");
        bookService.addRating(bookId, ratingDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{bookId}/comment")
    public ResponseEntity<?> commentOnBook(@PathVariable Long bookId, @RequestBody CommentDto commentDto) {
        bookService.addComment(bookId, commentDto);
        return ResponseEntity.ok().build();
    }


    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }


    @GetMapping("/{bookId}")
    public ResponseEntity<Book> getBookById(@PathVariable Long bookId) {
        Book book = bookService.getBookById(bookId);
        return ResponseEntity.ok(book);
    }

    @GetMapping("/{bookId}/comments")
    public ResponseEntity<List<CommentDto>> getCommentsForBook(@PathVariable Long bookId) {
        List<CommentDto> comments = commentService.getCommentsForBook(bookId);  // Assuming this method exists in RatingService
        return ResponseEntity.ok(comments);
    }

    @GetMapping("/{bookId}/ratings")
    public ResponseEntity<List<RatingDto>> getRatingsForBook(@PathVariable Long bookId) {
        List<RatingDto> ratings = ratingService.getRatingsForBook(bookId);  // Assuming this method exists in RatingService
        return ResponseEntity.ok(ratings);
    }



    @GetMapping("/{bookId}/average-rating")
    public ResponseEntity<Double> getAverageRating(@PathVariable Long bookId) {
        double averageRating = ratingService.calculateAverageRating(bookId);
        return ResponseEntity.ok(averageRating);
    }

    @GetMapping("/top-sellers")
    public ResponseEntity<List<Book>> getTop10BooksByCopiesSold() {
        List<Book> topSellers = bookService.getTop10BooksByCopiesSold();
        return ResponseEntity.ok(topSellers);
    }

    // Endpoint to get books by a minimum number of copies sold
    @GetMapping("/copies-sold/{minCopies}")
    public ResponseEntity<List<Book>> getBooksByCopiesSold(@PathVariable int minCopies) {
        List<Book> books = bookService.getBooksByCopiesSold(minCopies);
        return ResponseEntity.ok(books);
    }


}
