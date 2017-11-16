package Models;

public class produto {
	private int codProd;
	private String descricao;
	private int valor;
	
	public int getcodProd(){
		return this.codProd;
	}
	
	public String getdescricao(){
		return this.descricao;
	}
	
	public int getvalor(){
		return this.valor;
	}
	
	public void setcodProd(int codProd){
		this.codProd = codProd;
	}
	
		public void setdescricao(String descricao) {
		this.descricao = descricao;
	}
	 
	public void setvalor(int valor){
		this.valor = valor;
	}

    public static void main(String[] args) {
    	
    }
}