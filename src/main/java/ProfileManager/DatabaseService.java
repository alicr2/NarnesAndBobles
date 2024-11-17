package ProfileManager;

import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.SQLException;

@Service
public class DatabaseService {

    public void connect() {
        DatabaseConnection dbConnection = new DatabaseConnection(); // Create an instance
        try (Connection connection = dbConnection.getConnection()) { // Call getConnection() on the instance
            if (connection != null) {
                // Use the connection for your database operations
                System.out.println("Connection established successfully!");
            } else {
                System.out.println("Failed to establish connection.");
            }
        } catch (SQLException e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
        }
    }
}
