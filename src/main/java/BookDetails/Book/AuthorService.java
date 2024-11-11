package BookDetails.Book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public List<Author> getAuthorDetails() {
        return authorRepository.findAll();
    }

    public void addNewAuthor(Author author) {
        authorRepository.save(author);
    }
}