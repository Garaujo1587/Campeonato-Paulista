package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;


import persistence.RodadaDao;


public class ControllerRodada implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		try {
			geraRodadas();
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
	

}
