package persistence;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class RodadaDao {

private Connection c;
	
	public RodadaDao() throws ClassNotFoundException, SQLException {
		GenericDao gDao = new GenericDao();
		c = gDao.getConnection();
	}
	
	public String procGeraGrupos() throws SQLException {
		String sql = "{CALL sp_criando_rodadas (?)}";
		CallableStatement cs = c.prepareCall(sql);
		cs.registerOutParameter(1, Types.VARCHAR);
		cs.execute();
		String saida = cs.getString(1);
		cs.close();
		
		return saida;
	}
}
