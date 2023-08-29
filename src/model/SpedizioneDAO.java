package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SpedizioneDAO {
	private static final String TABLE_NAME = "spedizione";
	
	public static List<SpedizioneBean> doRetrieveAll() throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		
		List<SpedizioneBean> spedizioni = new ArrayList<>();
		
		String query = "SELECT * FROM " + TABLE_NAME;
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			ps = con.prepareStatement(query);
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				SpedizioneBean sped = new SpedizioneBean();
				sped.setCodice(rs.getInt("CodiceSpedizione"));
				sped.setCorriere(rs.getString("Corriere"));
				sped.setCosto(rs.getDouble("Costo"));
				sped.setTempo(rs.getString("Tempo"));
				sped.setTipo(rs.getString("Tipo"));
				spedizioni.add(sped);
			}
		} finally {
			try {
				if (ps != null)
					ps.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(con);
			}
		}
		return spedizioni;
	}
	
	public static SpedizioneBean doRetrieveByCode(int codice) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		
		String query = "SELECT * FROM " + TABLE_NAME + " WHERE CodiceSpedizione=?";
		SpedizioneBean bean = new SpedizioneBean();
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, codice);
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				bean.setCodice(rs.getInt("CodiceSpedizione"));
				bean.setTipo(rs.getString("Tipo"));
				bean.setCosto(rs.getDouble("Costo"));
				bean.setTempo(rs.getString("Tempo"));
				bean.setCorriere(rs.getString("Corriere"));
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
		
		return bean;
	}
}
