package BookDetails.Book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping
    public List<Author> getAuthorDetails() {
        return authorService.getAuthorDetails();
    }

    @PostMapping("/addNewAuthor")
    public void addNewAuthor(@RequestBody Author author) {
        authorService.addNewAuthor(author);
    }
}
