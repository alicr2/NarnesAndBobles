package book.ratings.project.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String comment;
    private Long userId;
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable= false)
    @JsonIgnore
    private Book book;


    public Comment (String comment, Long userId, LocalDateTime date, Book book){
        this.comment =comment;
        this.userId = userId;
        this.date =date;
        this.book = book;
    }

    public Comment() {

    }

    public Long getId(){
        return id;
    }

    public String getComment(){
        return comment;
    }

    public Long getUserId(){
        return userId;
    }

    public LocalDateTime getDate(){
        return date;
    }

    public Book getBook(){
        return book;
    }

    public void setId(Long id){
        this.id=id;
    }

    public void setComment(String comment){
        this.comment = comment;
    }

    public void setUserId(Long userId){
        this.userId=userId;
    }

    public void setDate(LocalDateTime date){
        this.date =date;
    }

    public void setBook(Book book){
        this.book = book;
    }

}
