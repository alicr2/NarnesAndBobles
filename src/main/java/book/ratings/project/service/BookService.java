package book.ratings.project.service;

import book.ratings.project.dto.RatingDto;
import book.ratings.project.dto.CommentDto;
import book.ratings.project.model.Book;
import book.ratings.project.model.Comment;
import book.ratings.project.model.Rating;
import book.ratings.project.repository.BookRepository;
import book.ratings.project.repository.CommentRepository;
import book.ratings.project.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final CommentRepository commentRepository;
    private final RatingRepository ratingRepository;

    @Autowired
    public BookService(BookRepository bookRepository, CommentRepository commentRepository, RatingRepository ratingRepository) {
        this.bookRepository = bookRepository;
        this.commentRepository = commentRepository;
        this.ratingRepository = ratingRepository;
    }


    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }


    public Book getBookById(Long bookId) {
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));
    }


    public void addRating(Long bookId, RatingDto ratingDto) {
        Rating rating = new Rating();
        rating.setRating(ratingDto.getRating());
        rating.setUserId(ratingDto.getUserId());
        rating.setDate(ratingDto.getDate());


        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        rating.setBook(book);

        ratingRepository.save(rating);
    }


    public void addComment(Long bookId, CommentDto commentDto) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        Comment comment = new Comment(commentDto.getComment(), commentDto.getUserId(), commentDto.getDate(), book);
        commentRepository.save(comment);
    }


    public List<CommentDto> getCommentsForBook(Long bookId) {
        List<Comment> comments = commentRepository.findByBook_Id(bookId);
        return comments.stream()
                .map(comment -> new CommentDto(comment.getComment(), comment.getUserId(), comment.getBook().getId(), comment.getDate())) // Fix here
                .collect(Collectors.toList());
    }


    public double calculateAverageRating(Long bookId) {
        List<Rating> ratings = ratingRepository.findByBookId(bookId);
        if (ratings.isEmpty()) {  // Only check for empty
            return 0.0;
        }
        double sum = ratings.stream().mapToDouble(Rating::getRating).sum();
        return sum / ratings.size();
    }


}
