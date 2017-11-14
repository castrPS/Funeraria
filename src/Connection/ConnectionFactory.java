package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class ConnectionFactory {
	String user = "g172if686cc_eq06";
	String password = "dbelrvuq";
	String url_driver = "jdbc:oracle:thin:@oracle11g.cin.ufpe.br:1521:oracle";
	public Connection getConnection() {
        try {
        	Class.forName("oracle.jdbc.driver.OracleDriver");
            return DriverManager.getConnection(url_driver, user, password) ;
        } catch (SQLException e) {
        	JOptionPane.showMessageDialog(null, e.getMessage());
        } catch (ClassNotFoundException e) {
        	JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return null;
    }
}
