package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import model.Times;
import persistence.GrupoDao;

public class ControllerGrupo implements ActionListener {

	private JTextArea taGrupo;
	private JTextArea tabGrupo;

	public ControllerGrupo(JTextArea taGrupos, JTextArea tabGrupo) {
		this.taGrupo = taGrupos;
		this.tabGrupo = tabGrupo;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		try {
			geraGrupos();
			mostraGrupos();
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

	private void mostraGrupos() throws ClassNotFoundException, SQLException {

		GrupoDao gDao = new GrupoDao();
		List<Times> listaTimes = gDao.mostraGrupos();

		taGrupo.setText("");
		tabGrupo.setText("");

		StringBuffer sb = new StringBuffer("Grupo\t\tTime\n\n");

		for (int i = 0; i < listaTimes.size(); i++) {

			sb.append(listaTimes.get(i).getGrupo() + "\t\t" + listaTimes.get(i).getNomeTime() + "\n");

			if ((i + 1) % 4 == 0) {

				sb.append("\n");
			}

		}

		taGrupo.setText(sb.toString());
		tabGrupo.setText(sb.toString());
	}
}
