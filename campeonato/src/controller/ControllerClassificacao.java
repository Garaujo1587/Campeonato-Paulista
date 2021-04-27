package controller;

import java.sql.SQLException;
import java.util.List;
import javax.swing.JTable;
import model.ModelResultados;
import model.ModeloTabelaResultados;
import persistence.RodadaDao;

public class ControllerClassificacao {

	public void buscaClassificacaoGrupos(JTable table,String grupo) throws ClassNotFoundException, SQLException {
		
		RodadaDao rd = new RodadaDao();
		

		
			mostraClassificacao(table, rd.classificacao(grupo));
		

	}

	private void mostraClassificacao(JTable table, List<ModelResultados> resultados) {

		ModeloTabelaResultados mTR = new ModeloTabelaResultados(resultados);
		table.setModel(mTR);
		table.getColumnModel().getColumn(0).setPreferredWidth(100);

	}

}
