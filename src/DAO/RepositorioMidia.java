package DAO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Blob;
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
	   "SELECT m.objeto, m.nome FROM MIDIA m WHERE m.cod_prod = ?";
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
		System.out.println(path);
		String[] point= path.split("\\.");
		System.out.println(point.length);
		String nome = produto.getdescricao();
		System.out.println(nome+"."+point[point.length-1]);
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
	 
	 public static String consultarCod(int cod) throws SQLException{
		 PreparedStatement ps;
		 ResultSet rs;
		 Blob b=null;
		 if(Base.getConnection() != null){
			 ps = Base.getConnection().prepareStatement(CONSULTA_MIDIA);
			 ps.setInt(1, cod);
			 rs = ps.executeQuery();
			 while(rs.next()){
				 b= rs.getBlob(1);
				 InputStream is = b.getBinaryStream();
	             try {
					FileOutputStream fos = new FileOutputStream((String) rs.getObject(2));
					 
					int bit = 0;
					while ((bit = is.read()) != -1)
					{
					    fos.write(bit); 
					}
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	             return (String) rs.getObject(2);
			 }
		 }
		return null;
		 
	 }
}

