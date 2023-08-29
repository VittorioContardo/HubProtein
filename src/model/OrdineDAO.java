package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrdineDAO {
	private static final String TABLE_NAME_ORDINE = "ordine";
	private static final String TABLE_NAME_COMP = "composizione";
	
	public static void doSave(OrdineBean ordine) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		//int last;
		String query = "INSERT INTO " + TABLE_NAME_ORDINE + " (DataOrdine, Stato, Spedizione, Utente, Indirizzo) VALUES (?,?,?,?,?)";
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			ps = con.prepareStatement(query);
			
			ps.setDate(1, ordine.getData_ordine());
			ps.setString(2, ordine.getStato());
			ps.setInt(3,  ordine.getSpedizione());
			ps.setString(4, ordine.getUtente());
			ps.setInt(5, ordine.getIndirizzo());
			
			ps.executeUpdate();
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
	}
	
	public static int getLastNumOrdine() throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		
		String query = "SELECT max(Id) AS max FROM " + TABLE_NAME_ORDINE;
		
		int value = 0;
		try {
			con = DriverManagerConnectionPool.getConnection();
			ps = con.prepareStatement(query);
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				value = rs.getInt("max");
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
		return value;
	}
	
	public static void insertProduct(OrdineBean ordine) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		String query = "INSERT INTO " + TABLE_NAME_COMP + " (codiceProdotto, formato, gusto_colore, IdOrdine, quantita) VALUES(?,?,?,?,?)";

		
		List<ProductCart> products = ordine.getProduct();
		try {
			con = DriverManagerConnectionPool.getConnection();
			ps = con.prepareStatement(query);
			for (ProductCart productCart : products) {
				ps.setInt(1, productCart.getProduct().getCodice());
				ps.setString(2, productCart.getProduct().getFormato());
				ps.setString(3, productCart.getProduct().getGusto_colore());
				ps.setInt(4, ordine.getId());
				ps.setInt(5, productCart.getQuantita());
				
				ps.executeUpdate();
				con.commit();
			}
		} finally {
			ps.close();
		}
		
	}
	
	public static List<OrdineBean> getOrdiniByUser(String user_mail) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		
		List<OrdineBean> ordini = new ArrayList<>();
		String query = "SELECT * FROM " + TABLE_NAME_ORDINE + " WHERE Utente=?";
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, user_mail);
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				OrdineBean bean = new OrdineBean();
				bean.setId(rs.getInt("Id"));
				bean.setData_ordine(rs.getDate("DataOrdine"));
				bean.setStato(rs.getString("Stato"));
				bean.setSpedizione(rs.getInt("Spedizione"));
				bean.setUtente("Utente");
				bean.setIndirizzo(rs.getInt("Indirizzo"));
				ordini.add(bean);
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
		
		for (OrdineBean ordineBean : ordini) {
			ordineBean.setProduct(getProductCartById(ordineBean.getId()));
		}
		System.out.println(ordini);
		return ordini;
	}
	
	public static OrdineBean getOrdineById(int id) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		
		OrdineBean bean = new OrdineBean();
		String query = "SELECT * FROM " + TABLE_NAME_ORDINE + " WHERE Id=?";
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				
				bean.setId(rs.getInt("Id"));
				bean.setData_ordine(rs.getDate("DataOrdine"));
				bean.setStato(rs.getString("Stato"));
				bean.setSpedizione(rs.getInt("Spedizione"));
				bean.setUtente("Utente");
				bean.setIndirizzo(rs.getInt("Indirizzo"));
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
		bean.setProduct(getProductCartById(bean.getId()));
		
		System.out.println(bean);
		return bean;
	}
	
	private static List<ProductCart> getProductCartById(int ordine) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		
		List<ProductCart> prodotti = new ArrayList<>();
		String query = "SELECT * FROM " + TABLE_NAME_COMP + " WHERE IdOrdine=?";
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, ordine);
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ProductBean bean = new ProductBean();
				bean = ProductDAO.doRetrieveByFullCode(rs.getInt("codiceProdotto"), rs.getString("formato"), rs.getString("gusto_colore"));
				ProductCart cart = new ProductCart(bean, rs.getInt("quantita"));
				prodotti.add(cart);
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
		
		return prodotti;
	}
	
	public static String getUserById(int idOrdine) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		
		OrdineBean bean = new OrdineBean();
		String query = "SELECT * FROM " + TABLE_NAME_ORDINE + " WHERE Id=?";
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, idOrdine);
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				
				bean.setId(rs.getInt("Id"));
				bean.setData_ordine(rs.getDate("DataOrdine"));
				bean.setStato(rs.getString("Stato"));
				bean.setSpedizione(rs.getInt("Spedizione"));
				bean.setUtente(rs.getString("Utente"));
				bean.setIndirizzo(rs.getInt("Indirizzo"));
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
		bean.setProduct(getProductCartById(bean.getId()));
		
		System.out.println(bean);
		return bean.getUtente();
	}
	
	public static List<OrdineBean> doRetrieveAll() throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		List<OrdineBean> ordini = new ArrayList<>();
		
		String query = "SELECT * FROM " + TABLE_NAME_ORDINE ;
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(query);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				OrdineBean bean = new OrdineBean();
				IndirizzoBean indirizzoBean = new IndirizzoBean();
				
				bean.setId(rs.getInt("Id"));
				bean.setData_ordine(rs.getDate("DataOrdine"));
				bean.setStato(rs.getString("Stato"));
				bean.setSpedizione(rs.getInt("Spedizione"));
				bean.setUtente(rs.getString("Utente"));
				bean.setIndirizzo(rs.getInt("Indirizzo"));
				bean.setProduct(getProductCartById(bean.getId()));
				ordini.add(bean);
			}
		}  finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		
		
		return ordini;
	}
}