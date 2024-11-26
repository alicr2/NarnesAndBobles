package wishlist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wishlist.model.WishList;

import java.util.List;

@Repository
public interface WishListRepository extends JpaRepository<WishList, Long> {
    // Finds all wishlists for a specific user by their userId
    List<WishList> findByUserId(Long userId);
}

