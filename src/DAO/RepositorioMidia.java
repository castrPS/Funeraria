package DAO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;


import Connection.Base;
import Models.Produto;

public class RepositorioMidia {
	 private static final String INSERIR_MIDIA =
	   "INSERT INTO midia ("
	   +"cod_prod, nome, objeto)"
	   +"values"+
	   "(?, ?, ?)";
	 private static final String CONSULTA_MIDIA =
	   "SELECT m.nome, m.objeto FROM MIDIA m WHERE m.cod_prod = ?";
	 //private static final String INSERIR_MIDIA ="INSERT INTO tb_midia (cod_prod, objeto) values (?, ?)";
	 
	 public static void main(String[] args){
		 try {
			RepositorioProduto.armazenar(null);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	 }
	 
	 public static void armazenar(Produto produto, String path) throws SQLException{
		PreparedStatement ps;
		ps = Base.getConnection().prepareStatement(INSERIR_MIDIA);
		ps.setInt(1, produto.getcodProd());
		String nome = produto.getdescricao();
		System.out.println(nome);
		ps.setString(2, nome);
		if(path!=null){
			File file= new File(path);
			try {
				InputStream is = new FileInputStream(file);
				ps.setBinaryStream(3, is, (int) file.length());
				ps.execute();
				ps.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
	        	JOptionPane.showMessageDialog(null, e.getMessage());
			}
			
		}
	 }
	 
	 public static Object consultarCod(int cod) throws SQLException{
		return null;
		 
	 }
}

