package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTextField;
import model.ModelResultados;
import persistence.RodadaDao;

public class Controller4Finais {
	
	public void busca4Finais(JTextField timeA, JTextField timeB, String grupo) throws ClassNotFoundException, SQLException {


		RodadaDao rd = new RodadaDao();
		List<ModelResultados> clasGrupo = new ArrayList<ModelResultados>();
		clasGrupo.addAll(rd.classificacao(grupo));

		timeA.setEditable(true);
		timeB.setEditable(true);
		
		timeA.setText(clasGrupo.get(0).getTime());
		timeB.setText(clasGrupo.get(1).getTime());
		
		timeA.setEditable(false);
		timeB.setEditable(false);
			
	}

}
