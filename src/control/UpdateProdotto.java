package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ProductBean;
import model.ProductDAO;

@WebServlet("/update")
public class UpdateProdotto extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer codice = Integer.parseInt(req.getParameter("codice"));
		String formato = req.getParameter("formato");
		String gusto_colore = req.getParameter("gusto_colore");
		String nome = req.getParameter("nome");
		String descrizione = req.getParameter("descrizione");
		Integer quantità = Integer.parseInt(req.getParameter("quantita_magazzino"));
		Double prezzo = Double.parseDouble(req.getParameter("prezzo"));
		Double iva = Double.parseDouble(req.getParameter("iva"));
		String vegan = req.getParameter("vegan");
		Integer categoria = Integer.parseInt(req.getParameter("categoria"));
		String immagine = req.getParameter("newImg");
		
		if(immagine.equals("")) {
			immagine = req.getParameter("oldImg");
			System.out.println(immagine);
		}
		

		boolean isVegan;
		if (vegan == null) isVegan = false; else isVegan = true;
		System.out.println("pp");
		try {
				ProductDAO.doUpdate(new ProductBean(codice, formato, gusto_colore, nome, descrizione, quantità, prezzo, iva, isVegan, categoria, immagine));
		} catch (NumberFormatException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		req.getSession().removeAttribute("products");
		try {
			req.getSession().setAttribute("products", ProductDAO.doRetrieveAll());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		resp.sendRedirect(req.getContextPath() + "/VisualizzaProdottiAdmin.jsp");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
	
	
}
