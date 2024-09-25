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
public class RatingService {

    private final RatingRepository ratingRepository;

    private final BookRepository bookRepository;


    @Autowired
    public RatingService(RatingRepository ratingRepository, BookRepository bookRepository) {
        this.ratingRepository = ratingRepository;
        this.bookRepository = bookRepository;
    }

    public void addRating(Long bookId, RatingDto ratingDto){
        Rating rating = new Rating();
        rating.setRating(ratingDto.getRating());
        rating.setUserId(ratingDto.getUserId());
        rating.setDate(ratingDto.getDate());
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        rating.setBook(book);

        ratingRepository.save(rating);
    }

public List<RatingDto> getRatingsForBook(Long bookId) {
    List<Rating> ratings = ratingRepository.findByBookId(bookId); // Assuming this method exists in your repository
    return ratings.stream()
            .map(rating -> new RatingDto(
                    rating.getRating(),
                    rating.getUserId(),
                    rating.getDate()))
            .collect(Collectors.toList());
}



    public double calculateAverageRating(Long bookId) {
        List<Rating> ratings = ratingRepository.findByBookId(bookId);
        if (ratings.isEmpty()) {
            return 0.0;
        }
        double sum = ratings.stream().mapToDouble(Rating::getRating).sum();
        return sum / ratings.size();
    }
}