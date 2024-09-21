package book.ratings.project;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.activemq.ActiveMQAutoConfiguration;
import org.springframework.boot.autoconfigure.jms.JmsAutoConfiguration;

@SpringBootApplication
public class BookRatingApplication {
    public static void main(String[] args) {
        SpringApplication.run(BookRatingApplication.class, args);
    }
}
