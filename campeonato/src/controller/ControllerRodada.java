package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import model.Jogos;
import model.ModeloTabelaJogos;
import persistence.RodadaDao;

public class ControllerRodada {

	public void geraRodadas(JTable table) throws ClassNotFoundException, SQLException {

		RodadaDao rDao = new RodadaDao();
		
		String saida = rDao.procGeraRodadas();

		List<Jogos> listaJogos = rDao.mostraRodadas();

		JOptionPane.showMessageDialog(null, saida, "MENSAGEM", JOptionPane.INFORMATION_MESSAGE);

		mostraRodadas(table, listaJogos);

	}

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

	private void mostraRodadas(JTable table, List<Jogos> jogos) throws ClassNotFoundException, SQLException {

		try {

			if (!jogos.get(0).getNomeTimeA().equalsIgnoreCase("-1")) {
				ModeloTabelaJogos mJ = new ModeloTabelaJogos(jogos);
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
