package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import Connection.ConnectionFactory;
import Models.Produto;

public class ProdutoDAO {
	private Connection connection;

	public ProdutoDAO() {
		this.connection = new ConnectionFactory().createConnection();
	}

	public void insere(Produto produto) {
		String sql = "insert into produtos" + "(codProd,descricao,valor)" + " values  (?,?,?)";

		try {
			// prepared statement para inserção
			PreparedStatement stmt = connection.prepareStatement(sql);

			// seta os valores
			stmt.setInt(1, produto.getcodProd());
			stmt.setString(2, produto.getdescricao());
			stmt.setDouble(3, produto.getvalor());

			// executa
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
