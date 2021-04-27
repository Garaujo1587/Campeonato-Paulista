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
import model.ModelResultados;

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
		sql = "SELECT id, Mandante, Visitante,Gol_M , Gol_V ,Estadio, Cidade, Dataj From fn_RetornaRodadas() ";
		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {

			Jogos j = new Jogos();
			j.setId(rs.getInt("id"));
			j.setNomeTimeA(rs.getString("Mandante"));
			j.setNomeTimeB(rs.getString("Visitante"));
			j.setGolA(rs.getInt("Gol_M"));
			j.setGolB(rs.getInt("Gol_V"));
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
		sql = "SELECT id, Mandante, Visitante, Gol_M , Gol_V, Estadio, Cidade, Dataj FROM fn_BuscaJogos(?) ";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, data);
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {

			Jogos j = new Jogos();

			j.setId(rs.getInt("id"));
			j.setNomeTimeA(rs.getString("Mandante"));
			j.setNomeTimeB(rs.getString("Visitante"));
			j.setGolA(rs.getInt("Gol_M"));
			j.setGolB(rs.getInt("Gol_V"));
			j.setEstadio(rs.getString("Estadio"));
			j.setCidade(rs.getString("Cidade"));
			j.setData(rs.getString("Dataj"));

			listaJogos.add(j);

		}

		rs.close();
		ps.close();

		return listaJogos;
	}

	public void insereGol(Jogos jogo) throws SQLException {

		String sql = "{CALL sp_insereGol (?, ?, ?)}";
		CallableStatement cs = c.prepareCall(sql);
		cs.setInt(1, jogo.getId());
		cs.setInt(2, jogo.getGolA());
		cs.setInt(3, jogo.getGolB());
		cs.execute();
		cs.close();

	}


	public List<ModelResultados> classificacao(String grupo) throws SQLException{
		
		List<ModelResultados> classificacao = new ArrayList<ModelResultados>();
		String sql;
		sql = "SELECT cod, id, time_nome, num_jogos, vitoria, empate, derrota, gol_pro, gol_com, saldo_gol, pontos FROM fn_Resultados(?) ";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, grupo);
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {

			ModelResultados r = new ModelResultados();

			r.setPosicao(rs.getInt("cod"));
			r.setId(rs.getInt("id"));
			r.setTime(rs.getString("time_nome"));
			r.setnJogos(rs.getInt("num_jogos"));
			r.setVitorias(rs.getInt("vitoria"));
			r.setEmpates(rs.getInt("empate"));
			r.setDerrotas(rs.getInt("derrota"));
			r.setGolPro(rs.getInt("gol_pro"));
			r.setGolCom(rs.getInt("gol_com"));
			r.setSaldoGol(rs.getInt("saldo_gol"));
			r.setPontos(rs.getInt("pontos"));
			

			classificacao.add(r);

		}

		rs.close();
		ps.close();

		return classificacao;
	}
}
