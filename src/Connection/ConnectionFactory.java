package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class ConnectionFactory {
	private static final String url_driver = "jdbc:oracle:thin:@oracle11g.cin.ufpe.br:1521:Instance01";
    private static final String user = "g172if686cc_eq06";
    private static final String password = "dbelrvuq";

	public static Connection createConnection() {
        try {
        	Class.forName("oracle.jdbc.OracleDriver");
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
