package BookDetails.Book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private final BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getBookDetails() {
        return bookRepository.findAll();
    }

    public void addNewBook(BookDTO bookDTO) {
        Book book = new Book();
        book.setISBN(bookDTO.getIsbn());
        book.setName(bookDTO.getBookName());
        book.setDescription(bookDTO.getBookDescription());
        book.setPrice(bookDTO.getPrice());
        book.setGenre(bookDTO.getGenre());
        book.setPublisher(bookDTO.getPublisher());
        book.setYearPublished(bookDTO.getYearPublished());
        book.setCopiesSold(bookDTO.getCopiesSold());

        Author author = authorRepository.findById(bookDTO.getAuthorId())
                .orElseThrow(() -> new IllegalArgumentException("Author ID not found: " + bookDTO.getAuthorId()));
        book.setAuthor(author);
        bookRepository.save(book);
    }

    public Optional<Book> getBookDetailsByIsbn(String id) {
        return bookRepository.findById(id);
    }

    public List<Book> getBookListByAuthor(Long authorId) {
        return bookRepository.findByAuthorId(authorId);
    }
}
