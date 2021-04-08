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
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT time1.nomeTime AS 'Mandante', time2.nomeTime AS 'Visitante', time1.estadio AS 'Estadio', time1.cidade AS 'Cidade', data AS 'Data' ");
		sql.append("FROM times AS time1 ");
		sql.append("INNER JOIN jogos ");
		sql.append("ON time1.codigoTime = jogos.codigoTimeA ");
		sql.append("INNER JOIN times AS time2 ");
		sql.append("ON time2.codigoTime = jogos.codigoTimeB ");
		sql.append("ORDER BY data ");
		PreparedStatement ps = c.prepareStatement(sql.toString());
		ResultSet rs = ps.executeQuery();
		
		while (rs.next()) {
		
		Jogos j = new Jogos();
		j.setNomeTimeA(rs.getString("Mandante"));
		j.setNomeTimeB(rs.getString("Visitante"));
		j.setEstadio(rs.getString("Estadio"));
		j.setCidade(rs.getString("Cidade"));
		j.setData(rs.getDate("Data"));
		
		listaJogos.add(j);
		
		}
		
		rs.close();
		ps.close();
		
		return listaJogos;
	}
	
	public List<Jogos> buscaRodada(String data) throws SQLException {
		List<Jogos> listaJogos = new ArrayList<Jogos>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT time1.nomeTime AS 'Mandante', time2.nomeTime AS 'Visitante', data AS 'Data' ");
		sql.append("FROM times AS time1 ");
		sql.append("INNER JOIN jogos ");
		sql.append("ON time1.codigoTime = jogos.codigoTimeA ");
		sql.append("INNER JOIN times AS time2 ");
		sql.append("ON time2.codigoTime = jogos.codigoTimeB ");
		sql.append("WHERE data = ? ");
		PreparedStatement ps = c.prepareStatement(sql.toString());
		ps.setString(1, data);
		ResultSet rs = ps.executeQuery();
		
		while (rs.next()) {
		
		Jogos j = new Jogos();	
			
		j.setNomeTimeA(rs.getString("Mandante"));
		j.setNomeTimeB(rs.getString("Visitante"));
		j.setData(rs.getDate("Data"));
		
		listaJogos.add(j);
		
		}
		
		rs.close();
		ps.close();
		
		return listaJogos;
	}
}
