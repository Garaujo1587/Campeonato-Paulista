package controller;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import model.Times;
import persistence.GrupoDao;

public class ControllerGrupo{



	

	public void geraGrupos(JTextArea taGrupo) throws ClassNotFoundException, SQLException {

		GrupoDao gDao = new GrupoDao();
		String saida = gDao.procGeraGrupos();
		JOptionPane.showMessageDialog(null, saida, "MENSAGEM", JOptionPane.INFORMATION_MESSAGE);
		
		mostraGrupos(taGrupo);

	}

	public void mostraGrupos(JTextArea taGrupo) throws ClassNotFoundException, SQLException {

		GrupoDao gDao = new GrupoDao();
		List<Times> listaTimes = gDao.mostraGrupos();

		taGrupo.setText("");
	

		StringBuffer sb = new StringBuffer("Grupo\t\tTime\n\n");

		for (int i = 0; i < listaTimes.size(); i++) {

			sb.append(listaTimes.get(i).getGrupo() + "\t\t" + listaTimes.get(i).getNomeTime() + "\n");

			if ((i + 1) % 4 == 0) {

				sb.append("\n");
			}

		}

		taGrupo.setText(sb.toString());
	
	}
}
