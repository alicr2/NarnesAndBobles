package book.ratings.project.dto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

public class RatingDto {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private double rating;
    private Long userId;
    private LocalDateTime date;

    private Long id;

    public RatingDto(double rating, Long userId, LocalDateTime date) {
        this.rating = rating;
        this.userId = userId;
        this.date = date;
    }

    public Long getUserId(){
        return userId;
    }

    public void setUserId(){
        this.userId=userId;
    }

    public double getRating(){
        return rating;
    }
    public void setRating(double rating){
        this.rating =rating;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getDate(){
        return date;
    }

    public void setDate(LocalDateTime date){
        this.date =date;
    }



}
