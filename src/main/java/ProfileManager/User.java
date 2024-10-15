package ProfileManager;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import java.util.List;
@Entity // Marks this class as a JPA entity (table in the database)
public class User {

    @Id // Marks the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generates an ID
    private Long id; // Unique ID for each user

    private String username;
    private String password;
    private String name;
    private String homeAddress;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<CreditCard> creditCards;
    // Default constructor (required for JPA)
    public User() {
    }

    // Parameterized constructor
    public User(String username, String password, String name, String homeAddress) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.homeAddress = homeAddress;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    // Optionally, you can override toString, equals, and hashCode methods for better debugging and comparison purposes.
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", homeAddress='" + homeAddress + '\'' +
                '}';
    }
}

