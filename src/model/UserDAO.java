package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
	private static final String TABLE_NAME = "utente";
	
	public static UserBean findByUsername(String email) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		
		UserBean user = new UserBean();
		String sql = "SELECT * FROM " + TABLE_NAME + " WHERE Email = ?";
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, email);
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				user.setEmail(rs.getString("Email"));
				user.setPassword(rs.getString("Password"));
				user.setCf(rs.getString("CF"));
				user.setNome(rs.getString("Nome"));
				user.setCognome(rs.getString("Cognome"));
				user.setTelefono(rs.getString("Telefono"));
				user.setAdmin(rs.getBoolean("Admin"));
			}
		} finally {
			try {
				if (ps != null) ps.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(con);
			}
		}
		return user;
	}
	
	public static boolean registerUser(UserBean user) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql = "INSERT INTO " + TABLE_NAME + " (Email, Password, CF, Nome, Cognome, Telefono, Admin) VALUE(?, ?, ?, ?, ?, ?, ?)";
		int result;
		try {
			con = DriverManagerConnectionPool.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setString(1, user.getEmail());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getCf());
			ps.setString(4, user.getNome());
			ps.setString(5, user.getCognome());
			ps.setString(6, user.getTelefono());
			ps.setBoolean(7, user.isAdmin());
			
			result = ps.executeUpdate();
			con.commit();;
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
	
	public static boolean doUpdateAccount(UserBean user) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql = "UPDATE " + TABLE_NAME + " SET CF=?, Nome=?, Cognome=?, Telefono=? WHERE Email=?";
		int result;
		try {
			con = DriverManagerConnectionPool.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setString(1, user.getCf());
			ps.setString(2, user.getNome());
			ps.setString(3, user.getCognome());
			ps.setString(4, user.getTelefono());
			ps.setString(5, user.getEmail());			
			result = ps.executeUpdate();
			con.commit();;
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
	
	public static UserBean findByIdOrdine(int idOrdine) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		
		String email = OrdineDAO.getUserById(idOrdine);
		System.out.println(email);
		UserBean user = new UserBean();
		String sql = "SELECT * FROM " + TABLE_NAME + " WHERE Email = ?";
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, email);
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				user.setEmail(rs.getString("Email"));
				user.setPassword(rs.getString("Password"));
				user.setCf(rs.getString("CF"));
				user.setNome(rs.getString("Nome"));
				user.setCognome(rs.getString("Cognome"));
				user.setTelefono(rs.getString("Telefono"));
				user.setAdmin(rs.getBoolean("Admin"));
			}
		} finally {
			try {
				if (ps != null) ps.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(con);
			}
		}
		return user;
	}
	
	public static List<UserBean> doRetrieveAll() throws SQLException{
		Connection con = null;
		PreparedStatement ps = null;
		List<UserBean> utenti = new ArrayList<>();
		
		String query = "SELECT * FROM " + TABLE_NAME; 
		
		try{
			con = DriverManagerConnectionPool.getConnection();
			ps = con.prepareStatement(query);
		
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				UserBean utente = new UserBean();
				utente.setEmail(rs.getString("Email"));
				utente.setCf(rs.getString("CF"));
				utente.setNome(rs.getString("Nome"));
				utente.setCognome(rs.getString("Cognome"));
				utente.setTelefono(rs.getString("Telefono"));
				utente.setAdmin(rs.getBoolean("Admin"));
				utenti.add(utente);
			}
			
		}finally {
			try {
				if (ps != null)
					ps.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(con);
			}
		}
		return utenti;
	}
}
