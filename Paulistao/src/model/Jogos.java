package model;

public class Jogos {

	private int id;
	private String nomeTimeA;
	private String nomeTimeB;
	private String estadio;
	private String cidade;
	private String data;
	private int golA;
	private int golB;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public String getData() {
		return data;
	}

	public void setData(String date) {
		this.data = date;
	}

	public int getGolA() {
		return golA;
	}

	public void setGolA(int golA) {
		this.golA = golA;
	}

	public int getGolB() {
		return golB;
	}

	public void setGolB(int golB) {
		this.golB = golB;
	}

	@Override
	public String toString() {
		return "Jogos [id=" + id + ", nomeTimeA=" + nomeTimeA + ", nomeTimeB=" + nomeTimeB + ", estadio=" + estadio
				+ ", cidade=" + cidade + ", data=" + data + ", golA=" + golA + ", golB=" + golB + "]";
	}

	

}
