package model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class ModeloTabelaJogos extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Jogos> jogos;
	private String[] colunas = { "Mandante", "Visitante", "Estadio", "Cidade", "Data" };
	
	public ModeloTabelaJogos(List<Jogos> jogos) {
		super();
		this.jogos = jogos;
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
			return jogos.get(linha).getNomeTimeB();
		case 2:
			return jogos.get(linha).getEstadio();
		case 3:
			return jogos.get(linha).getCidade();
		case 4:
			return jogos.get(linha).getData();
		}

		return null;
	}



}
