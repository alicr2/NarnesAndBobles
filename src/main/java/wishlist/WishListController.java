package wishlist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.util.List;

@RestController
@RequestMapping("/wishlist")
public class WishListController {

    @Autowired
    private WishListService wishListService;

    // Get all wishlists for a specific user
    @GetMapping("/user/{userId}")
    public List<WishList> getWishListsByUser(@PathVariable Long userId) {
        return wishListService.getWishListsByUser(userId);
    }

    // Create a new wishlist for a user with a given name
    @PostMapping("/create")
    public WishList createWishList(@RequestParam Long userId, @RequestParam String name) {
        return wishListService.createWishList(userId, name);
    }

    // Add books to an existing wishlist
    @PostMapping("/{wishListId}/add-books")
    public void addBooksToWishList(@PathVariable Long wishListId, @RequestParam List<Long> bookIds) {
        wishListService.addBooksToWishList(wishListId, bookIds);
    }

    // Remove a book from a wishlist
    @DeleteMapping("/{wishListId}/remove-book")
    public void removeBookFromWishList(@PathVariable Long wishListId, @RequestParam Long bookId) {
        wishListService.removeBookFromWishList(wishListId, bookId);
    }

    // Get a specific wishlist by ID
    @GetMapping("/{wishListId}")
    public WishList getWishListById(@PathVariable Long wishListId) {
        return wishListService.getWishListById(wishListId);
    }
}
