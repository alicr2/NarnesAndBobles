import org.example.model.Book;
import org.example.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    // Method to get books by genre
    public List<Book> getBooksByGenre(String genre) {
        return bookRepository.findByGenre(genre);
    }

    // Method to get top 10 best-selling books
    public List<Book> getTopSellers() {
        return bookRepository.findTop10ByOrderByCopiesSoldDesc();
    }

    // Method to get books with a rating greater than or equal to the specified rating
    public List<Book> getBooksByRating(double rating) {
        return bookRepository.findByRatingGreaterThanEqual(rating);
    }

    // Method to apply a discount to all books by a given publisher
    public void applyDiscount(String publisher, double discountPercent) {
        List<Book> books = bookRepository.findByPublisher(publisher);
        for (Book book : books) {
            double newPrice = book.getPrice() * (1 - discountPercent / 100);
            book.setPrice(newPrice);
            bookRepository.save(book);
        }
    }
}