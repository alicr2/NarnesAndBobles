package geekText;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wishlist")
public class WishListController {

    @Autowired
    private WishListService wishListService;

    // Create a new wishlist
    @PostMapping("/create")
    public ResponseEntity<Void> createWishList(@RequestParam Long userId, @RequestParam String wishListName) {
        wishListService.createWishList(userId, wishListName);
        return ResponseEntity.status(201).build();
    }

    // Add a book to a wishlist
    @PostMapping("/addBook")
    public ResponseEntity<Void> addBookToWishList(@RequestParam Long wishListId, @RequestParam Long bookId) {
        wishListService.addBookToWishList(wishListId, bookId);
        return ResponseEntity.ok().build();
    }

    // Remove a book from wishlist and move it to the shopping cart
    @DeleteMapping("/removeBook")
    public ResponseEntity<Void> removeBookFromWishList(@RequestParam Long wishListId, @RequestParam Long bookId) {
        wishListService.removeBookFromWishList(wishListId, bookId);
        return ResponseEntity.noContent().build();
    }

    // List all books in a wishlist
    @GetMapping("/books")
    public ResponseEntity<List<Book>> listBooksInWishList(@RequestParam Long wishListId) {
        List<Book> books = wishListService.listBooksInWishList(wishListId);
        return ResponseEntity.ok(books);
    }
}
