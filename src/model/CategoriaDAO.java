package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {
	private static final String TABLE_NAME = "categoria";
	
	public static List<CategoriaBean> doRetrieveAll() throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		
		String query = "SELECT * FROM " + TABLE_NAME;
		
		List<CategoriaBean> result = new ArrayList<CategoriaBean>();
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			ps = con.prepareStatement(query);
			
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				CategoriaBean bean = new CategoriaBean();
				bean.setCodice(rs.getInt("Codice"));
				bean.setNome(rs.getString("Nome"));
				bean.setDescrizione(rs.getString("Descrizione"));
				result.add(bean);
			}
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} finally {
				DriverManagerConnectionPool.releaseConnection(con);
			}
		}
		
		return result;
	}
	public static CategoriaBean doRetrieveByKey(int codice) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		
		String query = "SELECT * FROM " + TABLE_NAME +" WHERE Codice = ?";
		CategoriaBean bean = new CategoriaBean();;
		try {
			con = DriverManagerConnectionPool.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, codice);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				bean.setCodice(rs.getInt("Codice"));
				bean.setNome(rs.getString("Nome"));
				bean.setDescrizione(rs.getString("Descrizione"));
			}
		} catch (Exception e) {
			
		}
		System.out.println(bean.getNome());
		return bean;
	}
}
