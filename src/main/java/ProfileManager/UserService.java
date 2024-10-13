package ProfileManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    private CreditCardRepository creditCardRepository;


    // Method for creating a new user
    public void createUser(User user) {
        userRepository.save(user);
    }

    // Method for retrieving a user by username
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // Method for updating user fields (except email)
    public boolean updateUser(String username, User updatedUser) {
        User existingUser = userRepository.findByUsername(username);
        if (existingUser != null) {
            // Update fields (excluding email)
            existingUser.setUsername(updatedUser.getUsername());
            existingUser.setPassword(updatedUser.getPassword());
            existingUser.setName(updatedUser.getName());
            existingUser.setHomeAddress(updatedUser.getHomeAddress());

            userRepository.save(existingUser);  // Save the updated user
            return true;
        } else {
            return false;  // User not found
        }
    }
    public void addCreditCard(String username, CreditCard creditCard) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            creditCard.setUser(user);
            creditCardRepository.save(creditCard);
        }
    }
}
