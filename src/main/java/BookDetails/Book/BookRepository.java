package BookDetails.Book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {

   // @Query("SELECT s FROM Book s WHERE s.ISBN = ?1")
   Optional<Book> findBookByISBN(String ISBN);
}
