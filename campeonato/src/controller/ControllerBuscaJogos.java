package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JFormattedTextField;
import javax.swing.JTextArea;

import model.Jogos;
import persistence.RodadaDao;

public class ControllerBuscaJogos implements ActionListener {

	private JFormattedTextField tfData;
	private JTextArea taBusca;
	private String data;

	public ControllerBuscaJogos(String data, JTextArea taBusca) {
		this.data = data;
		this.taBusca = taBusca;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		try {
			buscaJogos();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void buscaJogos() throws ClassNotFoundException, SQLException {

		RodadaDao rDao = new RodadaDao();
		Jogos j = new Jogos();
		List<Jogos> listaJogos = rDao.buscaRodada(data);
		
		StringBuffer sb = new StringBuffer("Mandante\t\tVisitante\t\tData\n\n");

		for (Jogos jogos : listaJogos) {
			sb.append(jogos.getNomeTimeA() + "\t\t" + jogos.getNomeTimeB() + "\t\t" + jogos.getData() + "\n");
		}

		taBusca.setText(sb.toString());
	}

}
