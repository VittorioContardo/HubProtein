package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RecensioneDAO {
	private static final String TABLE_NAME = "recensione";
	
	public static void doSave(RecensioneBean recensione) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		
		String query = "INSERT INTO " + TABLE_NAME + " (Email, CodiceProdotto, Recensione, Valore) VALUES (?,?,?,?)";
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			ps = con.prepareStatement(query);
			
			
			ps.setString(1, recensione.getEmail());
			ps.setInt(2, recensione.getCodiceProdotto());
			ps.setString(3, recensione.getCommento());
			ps.setDouble(4, recensione.getValore());

			
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
	
	public static List<RecensioneBean> doRetrieveByUser(String email) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		
		List<RecensioneBean> recensioni = new ArrayList<>();
		
		String query = "SELECT * FROM " + TABLE_NAME + " WHERE Email=?";
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				RecensioneBean bean = new RecensioneBean();
				bean.setCodiceProdotto(rs.getInt("CodiceProdotto"));
				bean.setEmail(rs.getString("Email"));
				bean.setCommento(rs.getString("Recensione"));
				bean.setValore(rs.getInt("Valore"));
				recensioni.add(bean);
			}

		} finally {
			try {
				if (ps != null)
					ps.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(con);
			}
		}
		return recensioni;
	}
	
	public static List<RecensioneBean> doRetrieveByCode(int codice) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		
		String query = "SELECT * FROM " + TABLE_NAME + " WHERE CodiceProdotto=?";
		List<RecensioneBean> recensioni = new ArrayList<>();
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, codice);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				RecensioneBean bean = new RecensioneBean();
				bean.setCodiceProdotto(rs.getInt("CodiceProdotto"));
				bean.setEmail(rs.getString("Email"));
				bean.setCommento(rs.getString("Recensione"));
				bean.setValore(rs.getDouble("Valore"));
				recensioni.add(bean);
			}

		} finally {
			try {
				if (ps != null)
					ps.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(con);
			}
		}
		return recensioni;
	}
}
