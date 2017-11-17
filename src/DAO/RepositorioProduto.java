package DAO;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;


import Connection.Base;
import Models.Produto;

public class RepositorioProduto {
	 private static final String INSERIR_PRODUTO =
	   "INSERT INTO produto ("
	   +"cod_prod, descricao, valor)"
	   +"values"+
	   "(?, ?, ?)";
	 private static final String CONSULTA_PRODUTO =
	   "SELECT * FROM Produto p WHERE p.cod_prod = ?";
	 //private static final String INSERIR_MIDIA ="INSERT INTO tb_midia (cod_prod, objeto) values (?, ?)";
	 
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
		 PreparedStatement ps;
		 ResultSet rs;
		 if(Base.getConnection() != null){
			 ps = Base.getConnection().prepareStatement(CONSULTA_PRODUTO);
			 ps.setInt(1, cod);
			 rs = ps.executeQuery();
			 while(rs.next()){
				 if(rs!=null)
				 System.out.println("Produto" + rs.getObject(1));
				 Produto p= new Produto(((BigDecimal) rs.getObject(1)).intValue(), (String) rs.getObject(2),((BigDecimal) rs.getObject(3)).doubleValue());
				 return p;
			 }
		 }

		 return null;
	 }
}

