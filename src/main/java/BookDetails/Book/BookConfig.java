package BookDetails.Book;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class BookConfig {

    @Bean
    CommandLineRunner commandLineRunner(BookRepository repository) {
        return args-> {
            Book one = new Book(
                    "12345678ABC",
                    "The Great Gatsby",
                    "A description",
                    20.25,
                    "James Madison",
                    "Literature",
                    "Publishing",
                    1500,
                    400
            );

            Book two = new Book(
                    "9876543CBA",
                    "To Kill a Mockingbird",
                    "Some description",
                    19.05,
                    "George Washington",
                    "Alliteration",
                    "Published",
                    2100,
                    5000
            );

            repository.saveAll(
                    List.of(one, two)
            );
        };
    }
}
