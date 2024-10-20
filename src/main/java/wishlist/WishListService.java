package wishlist;

import books.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.List;

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
//        wishList.setUser(new wishlist.User(userId));  // Assuming user exists in the DB
        return wishListRepository.save(wishList);
    }

    // Add a book to a wishlist
    public void addBookToWishList(Long wishListId, Long bookId) {
//        WishList wishList = wishListRepository.findByUserId(wishListId).orElseThrow();
//        books.Book book = bookRepository.findBooksByGenre(bookId).orElseThrow();
//        wishList.getBooks().add(book);
//        wishListRepository.save(wishList);
    }

    // Remove a book from a wishlist and move it to a shopping cart
    public void removeBookFromWishList(Long wishListId, Long bookId) {
        WishList wishList = wishListRepository.findById(wishListId).orElseThrow();
        wishList.getBooks().removeIf(book -> book.getId().equals(bookId));
        wishListRepository.save(wishList);
    }

    // List all books in a wishlist
    public List<Book> listBooksInWishList(Long wishListId) {
        WishList wishList = wishListRepository.findById(wishListId).orElseThrow();
        return wishList.getBooks();
    }
}
