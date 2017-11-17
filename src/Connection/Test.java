package Connection;
import java.sql.Connection;
import java.util.Scanner;

import Connection.Base;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner in= new Scanner(System.in);
		String path = in.nextLine();
		String[] div= path.split(".");
		System.out.println(div.length);
	}

}
