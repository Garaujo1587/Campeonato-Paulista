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
	
	

	private void mostraRodadas(JTable table, List<Jogos> jogos) throws ClassNotFoundException, SQLException {

		
		ModeloTabelaJogos mJ = new ModeloTabelaJogos(jogos);
		table.setModel(mJ);

		
	

	
	}

}
