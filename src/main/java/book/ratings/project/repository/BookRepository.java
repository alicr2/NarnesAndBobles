package book.ratings.project.repository;
import book.ratings.project.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;



@Repository
public interface BookRepository  extends JpaRepository<Book,Long> {

    Optional<Book> findByTitle(String title);
    Optional<Book> findByAuthor(String author);





}
