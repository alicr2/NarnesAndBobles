package ProfileManager;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;


@RestController
@RequestMapping("/users")
public class UserControl {

    @Autowired
    private UserService userService;

    @Autowired
    private CreditCardService creditCardService;

    // Other endpoints...

    // Endpoint for creating a credit card for a user
    @PostMapping("/{username}/credit-cards")
    public ResponseEntity<String> createCreditCard(
            @PathVariable String username,
            @RequestBody CreditCard creditCard) {
        boolean isCreated = creditCardService.createCreditCardForUser(username, creditCard);
        if (isCreated) {
            return new ResponseEntity<>("Credit card added successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }
}
