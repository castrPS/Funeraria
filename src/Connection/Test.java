package Connection;
import java.sql.Connection;

import Connection.Base;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Connection con = Base.getConnection();
	}

}
