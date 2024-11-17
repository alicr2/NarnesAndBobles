package wishlist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import book.ratings.project.model.Book;

import java.util.List;

@RestController
@RequestMapping("/wishlist")
public class WishListController {

    @Autowired
    private WishListService wishListService;

    // Create a new wishlist
    @PostMapping("/create")
    public ResponseEntity<Void> createWishList(@RequestParam Long userId, @RequestParam String wishListName) {
        wishListService.createWishList(userId, wishListName);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // Add a book to a wishlist
    @PostMapping("/addBook")
    public ResponseEntity<Void> addBookToWishList(@RequestParam Long wishListId, @RequestParam Long bookId) {
        wishListService.addBookToWishList(wishListId, bookId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Remove a book from wishlist
    @DeleteMapping("/removeBook")
    public ResponseEntity<Void> removeBookFromWishList(@RequestParam Long wishListId, @RequestParam Long bookId) {
        wishListService.removeBookFromWishList(wishListId, bookId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // List all books in a wishlist
    @GetMapping("/books")
    public ResponseEntity<List<Book>> listBooksInWishList(@RequestParam Long wishListId) {
        List<Book> books = wishListService.listBooksInWishList(wishListId);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }
}
