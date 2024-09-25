package book.ratings.project.service;

import book.ratings.project.dto.CommentDto;
import book.ratings.project.model.Book;
import book.ratings.project.model.Comment;
import book.ratings.project.repository.BookRepository;
import book.ratings.project.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    private final BookRepository bookRepository;
    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(BookRepository bookRepository, CommentRepository commentRepository) {
        this.bookRepository = bookRepository;
        this.commentRepository = commentRepository;
    }

    public void addComment(Long bookId, CommentDto commentDto) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        Comment comment = convertToEntity(commentDto, book);
        commentRepository.save(comment);
    }


    public CommentDto getCommentDto(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        return convertToDto(comment);
    }


    public List<CommentDto> getCommentsForBook(Long bookId) {
        List<Comment> comments = commentRepository.findByBook_Id(bookId);
        return comments.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }



    public Comment convertToEntity(CommentDto commentDto, Book book) {
        Comment comment = new Comment();
        comment.setComment(commentDto.getComment());
        comment.setUserId(commentDto.getUserId());
        comment.setDate(commentDto.getDate());
        comment.setBook(book);
        return comment;
    }


    public CommentDto convertToDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setComment(comment.getComment());
        commentDto.setUserId(comment.getUserId());
        commentDto.setBookId(comment.getBook().getId());  // Get book ID from book entity
        commentDto.setDate(comment.getDate());
        return commentDto;
    }
}

