package book.ratings.project.model;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;



import java.util.List;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;

    private String title;
    private String author;

    private String genre;
    private double rating;

    @Column(name = "copies_sold")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer copiesSold;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comment> comments;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Rating> ratings;






    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getRating() {
        return rating;
    }


    public int getCopiesSold() {
        return copiesSold == null ? 0 : copiesSold;
    }

    public void setCopiesSold(Integer copiesSold) {
        this.copiesSold = copiesSold;
    }
    public void setRating(double rating) {
        this.rating = rating;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }


    public void addComment(Comment comment) {
        this.comments.add(comment);
        comment.setBook(this);
    }

    public void addRating(Rating rating) {
        this.ratings.add(rating);
        rating.setBook(this);
    }

}
