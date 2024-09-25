package book.ratings.project.dto;
import book.ratings.project.model.Book;

import java.time.LocalDateTime;


public class CommentDto {
    private String comment;
    private Long userId;
    private Long bookId;
    private LocalDateTime date;

    public CommentDto(){

    }

    public CommentDto(String comment,Long userId,Long bookId ,LocalDateTime date){
        this.comment =comment;
        this.userId= userId;
        this.bookId = bookId;
        this.date = date;
    }




    public String getComment(){
        return comment;
    }

    public void setComment(String comment){
        this.comment = comment;
    }

    public Long getUserId(){
        return userId;
    }

    public void setUserId(Long userId){
        this.userId = userId;
    }

    public Long getBookId(){
        return bookId;
    }

    public void setBookId(Long bookId){
        this.bookId = bookId;
    }

    public LocalDateTime getDate(){
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;

    }

    public void setbookId(Long bookId) {
        this.bookId= bookId;
    }
}
