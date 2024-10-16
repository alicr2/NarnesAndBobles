package wishlist;

import books.BookRepository;
//import book.ratings.project.repository.BookRepository;
//if i use Rayan's BookRepository the problem goes away.
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.List;
import java.util.ArrayList;

@Service
public class WishListService {

    @Autowired
    private WishListRepository wishListRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    // Fetch all wishlists for a given user
    public List<WishList> getWishListsByUser(Long userId) {
        return wishListRepository.findByUserId(userId);
    }

    // Create a new wishlist for a user
    public WishList createWishList(Long userId, String name) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Check if the user already has 3 wishlists
        if (user.getWishLists().size() >= 3) {
            throw new RuntimeException("User can only have up to 3 wishlists.");
        }

        // Create a new wishlist with the given name and save it
        WishList wishList = new WishList(name, user, new ArrayList<>());
        return wishListRepository.save(wishList);
    }

    // Add a book to a wishlist
    public void addBooksToWishList(Long wishListId, List<Long> bookIds) {
        WishList wishList = wishListRepository.findById(wishListId)
                .orElseThrow(() -> new RuntimeException("Wishlist not found"));

        List<Book> booksToAdd = bookRepository.findAllById(bookIds);
        wishList.getBooks().addAll(booksToAdd);
        wishListRepository.save(wishList);
    }

    // Remove a book from a wishlist
    public void removeBookFromWishList(Long wishListId, Long bookId) {
        WishList wishList = wishListRepository.findById(wishListId)
                .orElseThrow(() -> new RuntimeException("Wishlist not found"));

        Book bookToRemove = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        wishList.getBooks().remove(bookToRemove);
        wishListRepository.save(wishList);
    }

    // Get a specific wishlist by ID (to view it)
    public WishList getWishListById(Long wishListId) {
        return wishListRepository.findById(wishListId)
                .orElseThrow(() -> new RuntimeException("Wishlist not found"));
    }
}
