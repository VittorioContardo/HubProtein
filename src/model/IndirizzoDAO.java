package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IndirizzoDAO {
	private static final String TABLE_NAME = "indirizzo";
	
	public static void doSave(IndirizzoBean indirizzo) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		
		String query = "INSERT INTO " + TABLE_NAME + " (via, citta, stato_provincia, cap, paese, utente) VALUES (?,?,?,?,?,?)";
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			ps = con.prepareStatement(query);
			
			ps.setString(1, indirizzo.getVia());
			ps.setString(2, indirizzo.getCitta());
			ps.setString(3, indirizzo.getStato_provincia());
			ps.setString(4, indirizzo.getCap());
			ps.setString(5, indirizzo.getPaese());
			ps.setString(6, indirizzo.getUtente_mail());
			
			ps.executeUpdate();
			con.commit();
		} finally {
			try {
				if (ps != null)
					ps.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(con);
			}
		}
		
	}
	
	public static List<IndirizzoBean> doRetrieveByUser(String email) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		
		List<IndirizzoBean> indirizzi = new ArrayList<>();
		
		String query = "SELECT * FROM " + TABLE_NAME + " WHERE utente=?";
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				IndirizzoBean bean = new IndirizzoBean();
				bean.setCodice(rs.getInt("Codice"));
				bean.setVia(rs.getString("via"));
				bean.setCitta(rs.getString("citta"));
				bean.setStato_provincia(rs.getString("stato_provincia"));
				bean.setCap(rs.getString("cap"));
				bean.setPaese(rs.getString("paese"));
				bean.setUtente_mail(rs.getString("utente"));
				indirizzi.add(bean);
			}

		} finally {
			try {
				if (ps != null)
					ps.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(con);
			}
		}
		return indirizzi;
	}
	
	public static IndirizzoBean doRetrieveByCode(int codice) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		
		String query = "SELECT * FROM " + TABLE_NAME + " WHERE codice=?";
		IndirizzoBean bean = new IndirizzoBean();
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, codice);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				bean.setCodice(rs.getInt("codice"));
				bean.setVia(rs.getString("via"));
				bean.setCitta(rs.getString("citta"));
				bean.setStato_provincia(rs.getString("stato_provincia"));
				bean.setCap(rs.getString("cap"));
				bean.setPaese(rs.getString("paese"));
				bean.setUtente_mail(rs.getString("utente"));
			}

		} finally {
			try {
				if (ps != null)
					ps.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(con);
			}
		}
		return bean;
	}
	
	public static boolean doUpdateIndirizzo(IndirizzoBean indirizzo) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql = "UPDATE " + TABLE_NAME + " SET via=?, citta=?, stato_provincia=?, cap=?, paese=?, utente=? WHERE codice=?";
		int result;
		try {
			con = DriverManagerConnectionPool.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setString(1, indirizzo.getVia());
			ps.setString(2, indirizzo.getCitta());
			ps.setString(3, indirizzo.getStato_provincia());
			ps.setString(4, indirizzo.getCap());
			ps.setString(5, indirizzo.getPaese());
			ps.setString(6, indirizzo.getUtente_mail());
			ps.setInt(7, indirizzo.getCodice());
			
			result = ps.executeUpdate();
			con.commit();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} finally {
				DriverManagerConnectionPool.releaseConnection(con);
			}
		}
		return result == 1;
	}
}
