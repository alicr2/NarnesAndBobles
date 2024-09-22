package book.ratings.project.repository;
import book.ratings.project.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository

public interface RatingRepository extends JpaRepository<Rating, Long>{

    List<Rating> findByBookId(Long bookId);
    List<Rating> getRatingsByBookId(Long bookId);


    List<Rating> findRatingsByBookId(Long bookId);
}
