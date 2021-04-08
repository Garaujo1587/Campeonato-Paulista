package model;

import java.sql.Date;

public class Jogos {
	
	private String nomeTimeA;
	private String nomeTimeB;
	private String estadio;
	private String cidade;
	private Date data;
	
	public String getNomeTimeA() {
		return nomeTimeA;
	}
	public void setNomeTimeA(String nomeTimeA) {
		this.nomeTimeA = nomeTimeA;
	}
	public String getNomeTimeB() {
		return nomeTimeB;
	}
	public void setNomeTimeB(String nomeTimeB) {
		this.nomeTimeB = nomeTimeB;
	}
	public String getEstadio() {
		return estadio;
	}
	public void setEstadio(String estadio) {
		this.estadio = estadio;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "Jogos [nomeTimeA=" + nomeTimeA + ", nomeTimeB=" + nomeTimeB + ", estadio=" + estadio + ", cidade="
				+ cidade + ", data=" + data + "]";
	}
	

}
