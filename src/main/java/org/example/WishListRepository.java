package geekText;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface WishListRepository extends JpaRepository<WishList, Long> {
    List<WishList> findByUserId(Long userId);
    boolean existsByUserIdAndName(Long userId, String name);
}
