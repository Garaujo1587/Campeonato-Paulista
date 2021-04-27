package controller;

import java.awt.Color;
import java.awt.Component;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import model.ModelResultados;
import model.ModeloTabelaResultados;
import persistence.RodadaDao;

public class ControllerClassificacao {

	public void buscaClassificacaoGrupos(JTable table, String grupo) throws ClassNotFoundException, SQLException {

		String idRe1, idRe2;

		RodadaDao rd = new RodadaDao();
		List<ModelResultados> classifGeral = new ArrayList<ModelResultados>();
		classifGeral.addAll(rd.classificacao(""));

		idRe1 = classifGeral.get(14).getTime();
		idRe2 = classifGeral.get(15).getTime();

		mostraClassificacao(table, rd.classificacao(grupo), idRe1, idRe2);

	}

	private void mostraClassificacao(JTable table, final List<ModelResultados> resultados, final String r1,
			final String r2) {

		ModeloTabelaResultados mTR = new ModeloTabelaResultados(resultados);
		table.setModel(mTR);
		table.getColumnModel().getColumn(0).setPreferredWidth(100);

		table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			Color originalColor = null;

			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) super.getTableCellRendererComponent(
						table, value, isSelected, hasFocus, row, column);
				if (originalColor == null) {
					originalColor = getForeground();
				}
				if (value == null) {
					renderer.setText("");
				} else {
					renderer.setText(value.toString());
				}

				if (value.toString().equals(r1) || value.toString().equals(r2)) {
					renderer.setForeground(Color.RED);
				} else {
					renderer.setForeground(originalColor); // Retore original color
				}

				if (resultados.size() == 4) {
					if (row == 0 || row == 1) {
						renderer.setForeground(Color.blue);
					} else {
						if (value.toString().equals(r1) || value.toString().equals(r2)) {
							if (row == 2 || row == 3) {
								renderer.setForeground(Color.RED);
							}
						} else {
							renderer.setForeground(originalColor); // Retore original color
						}
					}
				}

				return renderer;
			}
		});
	}
}
