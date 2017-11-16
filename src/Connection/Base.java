package Connection;


import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Struct;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pedro on 28/11/16.
 */
public class Base {
    private static final Base ourInstance = new Base();
    private Connection connection;

    Base() {
        connection = ConnectionFactory.createConnection();
    }

    public static Connection getConnection() {
        try {
            if (ourInstance.connection == null || !ourInstance.connection.isValid(4))
                ourInstance.connection = ConnectionFactory.createConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ourInstance.connection;
    }
}