package model;

public class Grupos {
	
	private char grupo;
	private int codigoTime;
	
	public char getGrupo() {
		return grupo;
	}
	public void setGrupo(char grupo) {
		this.grupo = grupo;
	}
	public int getCodigoTime() {
		return codigoTime;
	}
	public void setCodigoTime(int codigoTime) {
		this.codigoTime = codigoTime;
	}
	
	@Override
	public String toString() {
		return "Grupos [grupo=" + grupo + ", codigoTime=" + codigoTime + "]";
	}
	
	

}
