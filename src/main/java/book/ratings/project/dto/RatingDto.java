package book.ratings.project.dto;

import java.time.LocalDateTime;

public class RatingDto {

    private double rating;
    private Long userId;
    private LocalDateTime date;

    private Long id;

    public RatingDto() {

    }
    public RatingDto(double rating, Long userId, LocalDateTime date) {
        this.rating = rating;
        this.userId = userId;
        this.date = date;
    }
    public RatingDto(double rating, Long userId) {
        this.rating = rating;
        this.userId = userId;
        this.date = LocalDateTime.now();
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
        this.date = date;
    }

    public void setCurrentDate(){this.date = LocalDateTime.now(); }



}
