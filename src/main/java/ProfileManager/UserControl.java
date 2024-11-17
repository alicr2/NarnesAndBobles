package ProfileManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserControl {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository; // Injected instance of UserRepository

    // Endpoint to create a new user
    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody User user) {
        if (userService.createUser(user)) {
            return new ResponseEntity<>("User created successfully", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("User creation failed", HttpStatus.BAD_REQUEST);
    }

    // Endpoint to get user by ID
    @GetMapping("/{id}") // Changed to just /{id}
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = userRepository.findById(id); // Use injected instance
        return user.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    // Endpoint to get user by username
    @GetMapping("/username/{username}") // Changed path to avoid conflict
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        User user = userService.findByUsername(username);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Endpoint to update user
    @PutMapping("/{username}")
    public ResponseEntity<String> updateUser(@PathVariable String username, @RequestBody User updatedUser) {
        boolean isUpdated = userService.updateUser(username, updatedUser); // Use injected instance
        return isUpdated ? new ResponseEntity<>("User updated successfully", HttpStatus.OK)
                : new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
    }

    // Endpoint to add credit card
    @PostMapping("/{username}/credit-cards")
    public ResponseEntity<String> addCreditCard(@PathVariable String username, @RequestBody CreditCard creditCard) {
        userService.addCreditCard(username, creditCard); // Use injected instance
        return new ResponseEntity<>("Credit card added successfully", HttpStatus.CREATED);
    }
}