package model;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class ModeloTabelaResultados extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<ModelResultados> jogos;
	private String[] colunas = { "Time", "Nj", "V", "E","D","GM", "GS", "SG", "P" };

		
	public ModeloTabelaResultados() {
		
	}

	public ModeloTabelaResultados(List<ModelResultados> jogos) {
		super();
		this.jogos = jogos;
	}

	@Override
	public String getColumnName(int column) {

		return colunas[column];
	}

	@Override
	public int getRowCount() {
		//return jogos.size();
		return 4;
	}

	@Override
	public int getColumnCount() {
		return colunas.length;
	}

	@Override
	public Object getValueAt(int linha, int coluna) {
		switch (coluna) {
		case 0:
			return jogos.get(linha).getTime();
		case 1:
			return jogos.get(linha).getnJogos();
		case 2:
			return jogos.get(linha).getVitorias();
		case 3:
			return jogos.get(linha).getEmpates();
		case 4:
			return jogos.get(linha).getDerrotas();
		case 5:
			return jogos.get(linha).getGolPro();
		case 6:
			return jogos.get(linha).getGolCom();
		case 7:
			return jogos.get(linha).getSaldoGol();
		case 8:
			return jogos.get(linha).getPontos();
		}

		return null;
	}

}
