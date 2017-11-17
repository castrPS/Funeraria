package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class ConnectionFactory {
	static String user = "g172if686cc_eq06";
	static String password = "dbelrvuq";
	static String url_driver = "jdbc:oracle:thin:@oracle11g.cin.ufpe.br:1521:oracle";
	public static Connection createConnection() {
        try {
        	Class.forName("oracle.jdbc.driver.OracleDriver");
            return DriverManager.getConnection(url_driver, user, password) ;
        } catch (SQLException e) {
        	JOptionPane.showMessageDialog(null, e.getMessage());
        	e.printStackTrace();
        } catch (ClassNotFoundException e) {
        	JOptionPane.showMessageDialog(null, e.getMessage());
        	e.printStackTrace();
		}
		return null;
    }
}
