package wishlist.service;

import book.ratings.project.model.Book;
import book.ratings.project.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wishlist.model.WishList;
import wishlist.repository.WishListRepository;

import java.util.Collection;

@Service
public class WishListService {

    @Autowired
    private WishListRepository wishListRepository;

    @Autowired
    private BookRepository bookRepository;

    // Create a new wishlist for a user
    public WishList createWishList(Long userId, String wishListName) {
        WishList wishList = new WishList();
        wishList.setName(wishListName);
        // Assuming User exists and you have a relationship
        // wishList.setUser(new wishlist.model.User(userId));
        return wishListRepository.save(wishList);
    }

    // Add a book to a wishlist
    public void addBookToWishList(Long wishListId, Long bookId) {
        WishList wishList = wishListRepository.findById(wishListId)
                .orElseThrow(() -> new RuntimeException("WishList not found"));
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        wishList.getBooks().add(book);  // Assuming getBooks() returns a collection of Book
        wishListRepository.save(wishList);
    }

    // Remove a book from a wishlist and move it to a shopping cart (e.g., by bookId)
    public void removeBookFromWishList(Long wishListId, Long bookId) {
        WishList wishList = wishListRepository.findById(wishListId)
                .orElseThrow(() -> new RuntimeException("WishList not found"));
        wishList.getBooks().removeIf(book -> book.getId().equals(bookId));
        wishListRepository.save(wishList);
    }

    // List all books in a wishlist
    public Collection<Book> listBooksInWishList(Long wishListId) {
        WishList wishList = wishListRepository.findById(wishListId)
                .orElseThrow(() -> new RuntimeException("WishList not found"));
        return wishList.getBooks();  // Assuming getBooks() returns a collection of Book
    }
}
