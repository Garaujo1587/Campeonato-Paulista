package persistence;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import model.Jogos;

public class RodadaDao {

	private Connection c;

	public RodadaDao() throws ClassNotFoundException, SQLException {
		GenericDao gDao = new GenericDao();
		c = gDao.getConnection();
	}

	public String procGeraRodadas() throws SQLException {
		String sql = "{CALL sp_criando_rodadas (?)}";
		CallableStatement cs = c.prepareCall(sql);
		cs.registerOutParameter(1, Types.VARCHAR);
		cs.execute();
		String saida = cs.getString(1);
		cs.close();

		return saida;
	}

	public List<Jogos> mostraRodadas() throws SQLException {
		List<Jogos> listaJogos = new ArrayList<Jogos>();
		String sql;
		sql = "SELECT Mandante, Visitante, Estadio, Cidade, Dataj From fn_RetornaRodadas() ";
		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {

			Jogos j = new Jogos();
			j.setNomeTimeA(rs.getString("Mandante"));
			j.setNomeTimeB(rs.getString("Visitante"));
			j.setEstadio(rs.getString("Estadio"));
			j.setCidade(rs.getString("Cidade"));
			j.setData(rs.getString("Dataj"));

			listaJogos.add(j);

		}

		rs.close();
		ps.close();

		return listaJogos;
	}

	public List<Jogos> buscaRodada(String data) throws SQLException {
		List<Jogos> listaJogos = new ArrayList<Jogos>();
		String sql;
		sql = "SELECT Mandante, Visitante, Estadio,Cidade, Dataj FROM fn_BuscaJogos(?) ";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, data);
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {

			Jogos j = new Jogos();

			j.setNomeTimeA(rs.getString("Mandante"));
			j.setNomeTimeB(rs.getString("Visitante"));
			j.setEstadio(rs.getString("Estadio"));
			j.setCidade(rs.getString("Cidade"));
			j.setData(rs.getString("Dataj"));

			listaJogos.add(j);

		}

		rs.close();
		ps.close();

		return listaJogos;
	}
}
