package ProfileManager;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class  CreditCardService {

    @Autowired
    private CreditCardRepository creditCardRepository;

    @Autowired
    private UserRepository userRepository;

    // Method to create a credit card for a user
    public boolean createCreditCardForUser(String username, CreditCard creditCard) {
        User user = userRepository.findByUsername(username);
        if (user!= null) {
            creditCard.setUser(user);  // Associate the credit card with the user
            creditCardRepository.save(creditCard);
            return true;
        } else {
            return false;  // User not found
        }
    }
}