package Connection;


import java.sql.Connection;
import java.sql.SQLException;

public class Base {
    private static final Base ourInstance = new Base();
    private Connection connection;

    private Base() {
        connection = ConnectionFactory.createConnection();
    }

    public static Connection getConnection() {
        try {
            if (ourInstance.connection == null || !ourInstance.connection.isValid(1))
                ourInstance.connection = ConnectionFactory.createConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ourInstance.connection;
    }
}