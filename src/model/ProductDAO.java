package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
	private static final String TABLE_NAME = "prodotto";
	private static final String galleryPath = "./img/";
	
	public static void doSave(ProductBean product) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;

		String insertSQL = "INSERT INTO " + TABLE_NAME + "(Codice, Formato, Gusto_Colore, Nome, Descrizione, QuantitaMagazzino, Prezzo, Iva, Vegan, Categoria, Immagine) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			con = DriverManagerConnectionPool.getConnection();
			ps = con.prepareStatement(insertSQL);
			ps.setInt(1, product.getCodice());
			ps.setString(2, product.getFormato());
			ps.setString(3, product.getGusto_colore());
			ps.setString(4, product.getNome());
			ps.setString(5, product.getDescrizione());
			ps.setInt(6, product.getQuantitaMagazzino());
			ps.setDouble(7, product.getPrezzo());
			ps.setDouble(8, product.getIva());
			ps.setBoolean(9, product.isVegan());
			ps.setInt(10, product.getCategoria());
			ps.setString(11, galleryPath + product.getImmagine());

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
	
	public static void doUpdate(ProductBean product) throws SQLException{
		Connection con = null;
		PreparedStatement ps = null;

		String SQL = "UPDATE " + TABLE_NAME + " SET Nome=?, Descrizione=?, QuantitaMagazzino=?, Prezzo=?, Iva=?, Vegan=?, Categoria=?, Immagine=? "
						+ " WHERE Codice=? AND Formato=? AND Gusto_Colore=?";
		
		//System.out.println(SQL);
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			ps = con.prepareStatement(SQL);
			ps.setString(1, product.getNome());
			ps.setString(2, product.getDescrizione());
			ps.setInt(3, product.getQuantitaMagazzino());
			ps.setDouble(4, product.getPrezzo());
			ps.setDouble(5, product.getIva());
			ps.setBoolean(6, product.isVegan());
			ps.setInt(7, product.getCategoria());
			if (product.getImmagine().contains(galleryPath)) {
				ps.setString(8, product.getImmagine());
			} else {
				ps.setString(8, galleryPath + product.getImmagine());
			}
			
			ps.setInt(9, product.getCodice());
			ps.setString(10, product.getFormato());
			ps.setString(11, product.getGusto_colore());
			
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
	
	public static ProductBean doRetrieveByFullCode(int codice, String formato, String gusto_colore) throws SQLException{
		Connection con = null;
		PreparedStatement ps = null;
		
		ProductBean bean = new ProductBean();

		String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE Codice=? AND Formato=? AND Gusto_Colore=?";

		try {
			con = DriverManagerConnectionPool.getConnection();
			ps = con.prepareStatement(selectSQL);
			ps.setInt(1, codice);
			ps.setString(2, formato);
			ps.setString(3, gusto_colore);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				bean.setCodice(rs.getInt("Codice"));
				bean.setFormato(rs.getString("Formato"));
				bean.setGusto_colore(rs.getString("Gusto_Colore"));
				bean.setNome(rs.getString("Nome"));
				bean.setDescrizione(rs.getString("Descrizione"));
				bean.setQuantitaMagazzino(rs.getInt("QuantitaMagazzino"));
				bean.setPrezzo(rs.getDouble("Prezzo"));
				bean.setIva(rs.getDouble("Iva"));
				bean.setVegan(rs.getBoolean("Vegan"));
				bean.setCategoria(rs.getInt("Categoria"));
				bean.setImmagine(rs.getString("Immagine"));
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
	
	public static List<ProductBean> doRetrieveByKey(int code) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;

		List<ProductBean> products = new ArrayList<>();

		String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE Codice = ?";

		try {
			con = DriverManagerConnectionPool.getConnection();
			ps = con.prepareStatement(selectSQL);
			ps.setInt(1, code);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				ProductBean bean = new ProductBean();
				
				bean.setCodice(rs.getInt("Codice"));
				bean.setFormato(rs.getString("Formato"));
				bean.setGusto_colore(rs.getString("Gusto_Colore"));
				bean.setNome(rs.getString("Nome"));
				bean.setDescrizione(rs.getString("Descrizione"));
				bean.setQuantitaMagazzino(rs.getInt("QuantitaMagazzino"));
				bean.setPrezzo(rs.getDouble("Prezzo"));
				bean.setIva(rs.getDouble("Iva"));
				bean.setVegan(rs.getBoolean("Vegan"));
				bean.setCategoria(rs.getInt("Categoria"));
				bean.setImmagine(rs.getString("Immagine"));
				products.add(bean);
			}

		} finally {
			try {
				if (ps != null)
					ps.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(con);
			}
		}
		return products;
	}
	
	public static List<ProductBean> doRetrieveAll() throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		List<ProductBean> products = new ArrayList<>();

		String selectSQL = "SELECT * FROM " + TABLE_NAME + " AS p1 NATURAL JOIN (SELECT codice, min(prezzo) AS Prezzo FROM " + TABLE_NAME + " GROUP BY Codice) AS p2 GROUP BY codice";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				ProductBean bean = new ProductBean();
				
				bean.setCodice(rs.getInt("Codice"));
				bean.setFormato(rs.getString("Formato"));
				bean.setGusto_colore(rs.getString("Gusto_Colore"));
				bean.setNome(rs.getString("Nome"));
				bean.setDescrizione(rs.getString("Descrizione"));
				bean.setQuantitaMagazzino(rs.getInt("QuantitaMagazzino"));
				bean.setPrezzo(rs.getDouble("Prezzo"));
				bean.setIva(rs.getDouble("Iva"));
				bean.setVegan(rs.getBoolean("Vegan"));
				bean.setCategoria(rs.getInt("Categoria"));
				bean.setImmagine(rs.getString("Immagine"));
				//System.out.println(bean);
				products.add(bean);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		//System.out.println(products);
		return products;
	}
	
	public static void doDelete(int codice, String formato, String gusto_colore) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;

		String SQL = "DELETE FROM " + TABLE_NAME + " WHERE Codice = ? AND Formato = ? AND Gusto_Colore = ?";

		try {
			con = DriverManagerConnectionPool.getConnection();
			ps = con.prepareStatement(SQL);
			ps.setInt(1, codice);
			ps.setString(2, formato);
			ps.setString(3, gusto_colore);
			
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
	
	public static ProductBean doRetrieveCheaperByCode(int codice) throws SQLException{
		Connection con = null;
		PreparedStatement ps = null;
		
		ProductBean bean = new ProductBean();

		String selectSQL = "SELECT *, min(prezzo) AS prezzo_minimo FROM " + TABLE_NAME + " WHERE Codice=?";

		try {
			con = DriverManagerConnectionPool.getConnection();
			ps = con.prepareStatement(selectSQL);
			ps.setInt(1, codice);
			
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				bean.setCodice(rs.getInt("Codice"));
				bean.setFormato(rs.getString("Formato"));
				bean.setGusto_colore(rs.getString("Gusto_Colore"));
				bean.setNome(rs.getString("Nome"));
				bean.setDescrizione(rs.getString("Descrizione"));
				bean.setQuantitaMagazzino(rs.getInt("QuantitaMagazzino"));
				bean.setPrezzo(rs.getDouble("prezzo_minimo"));
				bean.setIva(rs.getDouble("Iva"));
				bean.setVegan(rs.getBoolean("Vegan"));
				bean.setCategoria(rs.getInt("Categoria"));
				bean.setImmagine(rs.getString("Immagine"));
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
	
	public static List<String> getFormatiByCodeGusto(int codice, String gusto_colore) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		
		List<String> formati = new ArrayList<>();

		String selectSQL = "SELECT Formato FROM " + TABLE_NAME + " WHERE Codice=? AND Gusto_colore=?";

		try {
			con = DriverManagerConnectionPool.getConnection();
			ps = con.prepareStatement(selectSQL);
			ps.setInt(1, codice);
			ps.setString(2, gusto_colore);
			
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				String f = rs.getString("Formato");
				formati.add(f);
			}

		} finally {
			try {
				if (ps != null)
					ps.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(con);
			}
		}
		return formati;
	}
	
	public static void setQuantita(OrdineBean ordine) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		
		List<ProductCart> products = ordine.getProduct();

		String selectSQL = "UPDATE " + TABLE_NAME + " SET QuantitaMagazzino=? WHERE Codice=? AND Formato=? AND Gusto_Colore=?";

		try {
			con = DriverManagerConnectionPool.getConnection();
			ps = con.prepareStatement(selectSQL);
			
			for (ProductCart productCart : products) {
				ps.setInt(1, productCart.getProduct().getQuantitaMagazzino() - productCart.getQuantita());
				ps.setInt(2, productCart.getProduct().getCodice());
				ps.setString(3, productCart.getProduct().getFormato());
				ps.setString(4, productCart.getProduct().getGusto_colore());
				
				ps.executeUpdate();
				con.commit();
			}

		} finally {
			try {
				if (ps != null)
					ps.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(con);
			}
		}
	}
	
	public static List<ProductBean> searchByString(String word) {
        Connection con = null;
        PreparedStatement ps = null;

        List<ProductBean> list = new ArrayList<>();

        String query = "SELECT * FROM " + TABLE_NAME + " WHERE NOME like \"%\" ? \"%\" GROUP BY Codice";
        try {
            con = DriverManagerConnectionPool.getConnection();
            ps = con.prepareStatement(query);
            
            ps.setString(1, word);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ProductBean bean = new ProductBean();

                bean.setCodice(rs.getInt("Codice"));
                bean.setFormato(rs.getString("Formato"));
                bean.setGusto_colore(rs.getString("Gusto_Colore"));
                bean.setNome(rs.getString("Nome"));
                bean.setDescrizione(rs.getString("Descrizione"));
                bean.setQuantitaMagazzino(rs.getInt("QuantitaMagazzino"));
                bean.setPrezzo(rs.getDouble("Prezzo"));
                bean.setIva(rs.getDouble("Iva"));
                bean.setVegan(rs.getBoolean("Vegan"));
                bean.setCategoria(rs.getInt("Categoria"));
                bean.setImmagine(rs.getString("Immagine"));

                list.add(bean);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}