package ProfileManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CreditCardRepository creditCardRepository; // Make sure to autowire this

    // Method for creating a new user
    public boolean createUser(User user) {
        userRepository.save(user);
        return true; // Return true to indicate success
    }
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }


    // Method for retrieving a user by username
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // Method for updating user fields (excluding email)
    public boolean updateUser(String username, User updatedUser) {
        User existingUser = userRepository.findByUsername(username);
        if (existingUser != null) {
            // Update fields
            existingUser.setUsername(updatedUser.getUsername());
            existingUser.setPassword(updatedUser.getPassword());
            existingUser.setName(updatedUser.getName());
            existingUser.setHomeAddress(updatedUser.getHomeAddress());

            userRepository.save(existingUser);  // Save the updated user
            return true; // Return true to indicate success
        } else {
            return false;  // User not found
        }
    }

    // Method to add a credit card for a user
    public void addCreditCard(String username, CreditCard creditCard) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            creditCard.setUser(user); // Assuming you have set up a proper relationship
            creditCardRepository.save(creditCard);
        }
    }
}
