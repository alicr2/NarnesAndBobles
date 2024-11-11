package BookDetails.Book;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Author {
    @Id
    private Long id;
    private String firstName;
    private String lastName;
    private String biography;
    private String publisher;

    public Author() {
    }

    public Author(Long id, String firstName, String lastName, String biography, String publisher) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.biography = biography;
        this.publisher = publisher;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", biography='" + biography + '\'' +
                ", publisher='" + publisher + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getBiography() {
        return biography;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

}
