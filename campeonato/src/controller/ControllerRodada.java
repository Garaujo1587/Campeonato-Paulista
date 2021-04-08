package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import model.Jogos;
import persistence.RodadaDao;


public class ControllerRodada implements ActionListener {

	private JTextArea taRodadas;
	
	public ControllerRodada(JTextArea taRodadas) {
		this.taRodadas = taRodadas;
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
		
		taRodadas.setText("");
		
		StringBuffer sb = new StringBuffer("Mandante\t\tVisitante\t\tEstadio\t\tCidade\t\tData\n\n");
		
		for (Jogos j: listaJogos) {
			sb.append(j.getNomeTimeA() +j.getNomeTimeB()+j.getEstadio()+j.getCidade()+ j.getData()+"\n");
		}
		
		taRodadas.setText(sb.toString());
	}
	

}
