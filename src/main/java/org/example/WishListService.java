package geekText;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class WishListService {

    @Autowired
    private WishListRepository wishListRepository;

    @Autowired
    private BookRepository bookRepository;

    // Create a new wishlist for a user
    public WishList createWishList(Long userId, String wishListName) {
        if (wishListRepository.findByUserId(userId).size() >= 3) {
            throw new IllegalStateException("A user cannot have more than 3 wishlists.");
        }
        if (wishListRepository.existsByUserIdAndName(userId, wishListName)) {
            throw new IllegalStateException("Wishlist name must be unique for each user.");
        }
        WishList wishList = new WishList();
        wishList.setName(wishListName);
        wishList.setUser(new User(userId));  // Assuming user exists
        return wishListRepository.save(wishList);
    }

    // Add a book to a wishlist
    public void addBookToWishList(Long wishListId, Long bookId) {
        WishList wishList = wishListRepository.findById(wishListId)
                .orElseThrow(() -> new ResourceNotFoundException("Wishlist not found with id: " + wishListId));
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + bookId));
        wishList.getBooks().add(book);
        wishListRepository.save(wishList);
    }

    // Remove a book from wishlist
    public void removeBookFromWishList(Long wishListId, Long bookId) {
        WishList wishList = wishListRepository.findById(wishListId)
                .orElseThrow(() -> new ResourceNotFoundException("Wishlist not found with id: " + wishListId));
        wishList.getBooks().removeIf(book -> book.getId().equals(bookId));
        wishListRepository.save(wishList);
    }

    // List all books in a wishlist
    public List<Book> listBooksInWishList(Long wishListId) {
        WishList wishList = wishListRepository.findById(wishListId)
                .orElseThrow(() -> new ResourceNotFoundException("Wishlist not found with id: " + wishListId));
        return wishList.getBooks();
    }
}
