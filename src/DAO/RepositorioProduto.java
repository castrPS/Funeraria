package Repositories;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import Connection.Base;
import Models.Produto;

public class RepositorioProduto {
	 private static final String SELECT_PRODUTO_POR_COD = 
	   "SELECT * FROM Produto p WHERE p.cod_prod = ?";
	 private static final String INSERIR_PRODUTO = 
	   "INSERT INTO tb_produto ("
	   +"cod_prod, descricao, valor)"
	   +"values"+
	   "(?, ?, ?)";
	 private static final String CONSULTA_PRODUTO =
	   "SELECT * FROM Produto p WHERE p.cod_prod = ?";
	 
	 public static void main(String[] args){
		 try {
			RepositorioProduto.armazenar(null);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	 }
	 
	 public static void armazenar(Produto produto) throws SQLException{
		PreparedStatement ps;
		ps = Base.getConnection().prepareStatement(INSERIR_PRODUTO);
		ps.setInt(1, produto.getcodProd());
		ps.setString(2, produto.getdescricao());
		ps.setDouble(3, produto.getvalor());
		ps.executeUpdate();
		ps.close();
	 }
	 
	 public static Produto consultarCod(int cod) throws SQLException{
		 Produto produto;
		 if(Base.getConnection() != null){
			 
		 }
		 return produto;
	 }
}

