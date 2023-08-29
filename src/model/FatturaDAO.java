package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FatturaDAO {
	private static final String TABLE_NAME_FATT = "fattura";
	private static final String TABLE_NAME_PROD = "prodotto_fatturato";
	
	public static void doSave(FatturaBean fattura) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		
		String query = "INSERT INTO " + TABLE_NAME_FATT + " (importo, metodo_pagamento, data, idOrdine) VALUES (?,?,?,?)";
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			ps = con.prepareStatement(query);
			
			ps.setDouble(1, fattura.getImporto());
			ps.setString(2, fattura.getMetodo_pagamento());
			ps.setDate(3,  fattura.getData());
			ps.setInt(4, fattura.getIdOrdine());
			
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
	
	public static int getLastNumFattura() throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		
		String query = "SELECT max(idFattura) AS max FROM " + TABLE_NAME_FATT;
		
		int value = -1;
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
	
	public static void insertProduct(FatturaBean fattura) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		String query = "INSERT INTO " + TABLE_NAME_PROD + " (codiceProdotto, formato, gusto_colore, nomeProdotto, prezzo, iva, quantita, idFattura) VALUES(?,?,?,?,?,?,?,?)";
		
		
		List<ProductCart> products = fattura.getProdotti_fatturati();
		try {
			con = DriverManagerConnectionPool.getConnection();
			ps = con.prepareStatement(query);
			for (ProductCart productCart : products) {
				ps.setInt(1, productCart.getProduct().getCodice());
				ps.setString(2, productCart.getProduct().getFormato());
				ps.setString(3, productCart.getProduct().getGusto_colore());
				ps.setString(4, productCart.getProduct().getNome());
				ps.setDouble(5, productCart.getProduct().getPrezzo());
				ps.setDouble(6, productCart.getProduct().getIva());
				ps.setInt(7, productCart.getQuantita());
				ps.setInt(8, fattura.getIdFattura());
				
				ps.executeUpdate();
				con.commit();
			}
		} finally {
			ps.close();
		}
		
	}
	
	public static FatturaBean doRetrieveByCode(int idFattura) {
		Connection con = null;
		PreparedStatement ps = null;
		String query = "SELECT * FROM " + TABLE_NAME_FATT + " WHERE idFattura = ?";
		
		FatturaBean bean = new FatturaBean();
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			ps = con.prepareStatement(query);
			
			ps.setInt(1, idFattura);
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				bean.setIdFattura(rs.getInt("idFattura"));
				bean.setImporto(rs.getDouble("importo"));
				bean.setMetodo_pagamento(rs.getString("metodo_pagamento"));
				bean.setData(rs.getDate("data"));
				bean.setIdOrdine(rs.getInt("idOrdine"));
				bean.setProdotti_fatturati(getProdottiById(idFattura));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return bean;
	}
	
	private static List<ProductCart> getProdottiById(int idFattura) {
		Connection con = null;
		PreparedStatement ps = null;
		String query = "SELECT * FROM " + TABLE_NAME_PROD + " WHERE idFattura = ?";
		
		List<ProductCart> productCarts = new ArrayList<ProductCart>();
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			ps = con.prepareStatement(query);
			
			ps.setInt(1, idFattura);
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ProductBean product = new ProductBean();
				product.setCodice(rs.getInt("codiceProdotto"));
				product.setFormato(rs.getString("formato"));
				product.setGusto_colore(rs.getString("gusto_colore"));
				product.setNome(rs.getString("nomeProdotto"));
				product.setPrezzo(rs.getDouble("prezzo"));
				product.setIva(rs.getDouble("iva"));
				
				int quantita = rs.getInt("quantita");
				
				ProductCart bean = new ProductCart(product, quantita);
				productCarts.add(bean);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return productCarts;
	}
	
	public static int getIdFatturaByIdOrdine(int idOrdine) {
		Connection con = null;
		PreparedStatement ps = null;
		String query = "SELECT idFattura FROM " + TABLE_NAME_FATT + " WHERE idOrdine = ?";
		
		int idFattura = -1;
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			ps = con.prepareStatement(query);
			
			ps.setInt(1, idOrdine);
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				idFattura = rs.getInt("idFattura");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return idFattura;
	}
}
