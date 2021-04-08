package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

import model.Jogos;
import model.ModeloTabelaJogos;
import persistence.RodadaDao;

public class ControllerRodada implements ActionListener {

	private JTable table;

	

	public ControllerRodada(JTable table) {
		this.table = table;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		try {
			geraRodadas();
			mostraRodadas();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private void geraRodadas() throws ClassNotFoundException, SQLException {

		RodadaDao rDao = new RodadaDao();
		String saida = rDao.procGeraRodadas();
		JOptionPane.showMessageDialog(null, saida, "MENSAGEM", JOptionPane.INFORMATION_MESSAGE);

	}

	private void mostraRodadas() throws ClassNotFoundException, SQLException {

		RodadaDao rDao = new RodadaDao();
		List<Jogos> listaJogos = rDao.mostraRodadas();
		
		ModeloTabelaJogos mJ = new ModeloTabelaJogos(listaJogos);
		table.setModel(mJ);

		
	

	
	}

}
