package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import persistence.GrupoDao;

public class ControllerGrupo implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		try {
			geraGrupos();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	private void geraGrupos() throws ClassNotFoundException, SQLException {
		
		GrupoDao gDao = new GrupoDao();
		String saida = gDao.procGeraGrupos();
		JOptionPane.showMessageDialog(null, saida, "MENSAGEM", JOptionPane.INFORMATION_MESSAGE);
		
	}
}