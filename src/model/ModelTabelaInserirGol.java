package model;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class ModelTabelaInserirGol extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Jogos> jogos;
	private String[] colunas = { "Mandante", "Gol", "Gol", "Visitante", "Data" };

	public ModelTabelaInserirGol(List<Jogos> jogos) {
		super();
		this.jogos = jogos;
	}

	public Jogos getSelectedObject(int row) {
		return jogos.get(row);
	}

	@Override
	public String getColumnName(int column) {

		return colunas[column];
	}

	@Override
	public int getRowCount() {
		return jogos.size();
	}

	@Override
	public int getColumnCount() {
		return colunas.length;
	}

	@Override
	public Object getValueAt(int linha, int coluna) {
		switch (coluna) {
		case 0:
			return jogos.get(linha).getNomeTimeA();
		case 1:
			if (jogos.get(linha).getGolA() == -1) {
				return "-";
			} else {
				return jogos.get(linha).getGolA();
			}
		case 2:
			if (jogos.get(linha).getGolB() == -1) {
				return "-";
			} else {
				return jogos.get(linha).getGolB();
			}
		case 3:
			return jogos.get(linha).getNomeTimeB();
		case 4:
			return jogos.get(linha).getData();
		}

		return null;
	}

}
