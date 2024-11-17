package ProfileManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class DatabaseConnection {

    String driver = "com.mysql.jdbc.Driver";
     static final String URL = "jdbc:mysql://localhost:3306/ProfileManager";
     static final String USER = "root"; // Your MySQL username
     static final String PASSWORD = "SM305duh!"; // Your MySQL password

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
