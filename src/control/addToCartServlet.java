package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Carrello;
import model.ProductBean;
import model.ProductDAO;

@WebServlet("/addToCart")
public class addToCartServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String codice = req.getParameter("codice");
		String formato = req.getParameter("formato");
		String gusto_colore = req.getParameter("gusto_colore");
		String quantita = req.getParameter("quantita");
		
		ProductBean product =  null;
		try {
			product = ProductDAO.doRetrieveByFullCode(Integer.parseInt(codice), formato, gusto_colore);
		} catch (NumberFormatException | SQLException e) {
			req.getSession().setAttribute("errore", "Errore nell'inserimento nel carrello!");
			resp.sendRedirect(req.getContextPath() + "/error.jsp");
			e.printStackTrace();
		}
		Carrello c = (Carrello) req.getSession().getAttribute("carrello");
		if (c != null) {
			c.addToCart(product, Integer.parseInt(quantita));
		}
		resp.sendRedirect(req.getContextPath() + "/dettaglio-prodotto.jsp");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
	
	
}
