package controller;

import java.awt.TextField;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import model.Jogos;
import model.ModelTabelaResultado;
import persistence.RodadaDao;

public class ControllerInserirResultados {
	
	private ModelTabelaResultado mJ;

	public void buscaRodadas(JTable table, String data) throws ClassNotFoundException, SQLException {

		RodadaDao rDao = new RodadaDao();
		List<Jogos> rodadas = new ArrayList<Jogos>();
		rodadas = rDao.buscaRodada(data);

		mostraRodadas(table, rodadas);

	}

	public void buscaRodadas(JTable table) throws ClassNotFoundException, SQLException {

		RodadaDao rDao = new RodadaDao();
		List<Jogos> rodadas = new ArrayList<Jogos>();
		rodadas = rDao.mostraRodadas();

		mostraRodadas(table, rodadas);

	}
	
	public void inseriGols(int linha, int golA, int golB, String data, JTable table) throws SQLException, ClassNotFoundException {
		
		Jogos jogo = this.mJ.getSelectedObject(linha);
		RodadaDao rDao = new RodadaDao();
		
		jogo.setGolA(golA);
		jogo.setGolB(golB);
	
		rDao.insereGol(jogo);
		
		if(data.equals("-1")) {
			this.buscaRodadas(table);
		}else {
			this.buscaRodadas(table, data);
		}
		
	}

	private void mostraRodadas(JTable table, List<Jogos> jogos) throws ClassNotFoundException, SQLException {

		try {

			if (!jogos.get(0).getNomeTimeA().equalsIgnoreCase("-1")) {
				this.mJ = new ModelTabelaResultado(jogos);
				table.setModel(mJ);
			} else {
				table.clearSelection();

				StringBuffer sb = new StringBuffer();

				sb.append("Datas dos jogos \n\n");

				for (int i = 0; i < jogos.size(); i++) {
					sb.append(jogos.get(i).getData() + "\n");

				}

				JOptionPane.showMessageDialog(null, sb.toString(), "Data não existe", JOptionPane.ERROR_MESSAGE);
			}

		} catch (IndexOutOfBoundsException e) {

			JOptionPane.showMessageDialog(null, "crie novas rodadas", "Rodada não existe", JOptionPane.ERROR_MESSAGE);

		}

	}

}
