package persistence;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import model.Times;

public class GrupoDao {

private Connection c;
	
	public GrupoDao() throws ClassNotFoundException, SQLException {
		GenericDao gDao = new GenericDao();
		c = gDao.getConnection();
	}
	
	public String procGeraGrupos() throws SQLException {
		String sql = "{CALL sp_criando_grupos (?)}";
		CallableStatement cs = c.prepareCall(sql);
		cs.registerOutParameter(1, Types.VARCHAR);
		cs.execute();
		String saida = cs.getString(1);
		cs.close();
		
		return saida;
	}
	
	public Times mostraGrupos() throws SQLException {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT t.grupo AS grupo, t.nomeTime AS time, t.codigoTime AS codigoTime FROM times t ");
		PreparedStatement ps = c.prepareStatement(sql.toString());
		ResultSet rs = ps.executeQuery();
		
		Times t = new Times();
		
		t.setGrupo(rs.getString("grupo"));
		t.setNomeTime(rs.getString("time"));
		t.setCodigoTime(rs.getInt("codigoTime"));
		
		rs.close();
		ps.close();
		
		return t;
	}
}
