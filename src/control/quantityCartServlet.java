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

@WebServlet("/quantityCart")
public class quantityCartServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String codice = req.getParameter("codice");
		String formato = req.getParameter("formato");
		String gusto_colore = req.getParameter("gusto_colore");
		String action = req.getParameter("action");
		
		
		Carrello carrello = (Carrello) req.getSession().getAttribute("carrello");
		if (carrello == null) {
			resp.sendRedirect(req.getContextPath() + "/index.jsp");
			return;
		}
		ProductBean product = null;
		try {
			product = ProductDAO.doRetrieveByFullCode(Integer.parseInt(codice), formato, gusto_colore);
		} catch (NumberFormatException | SQLException e) {
			req.getSession().setAttribute("errore", "Errore nel carrello");
			resp.sendRedirect(req.getContextPath() + "/error.jsp");
			e.printStackTrace();
		}
		if (action != null) {
			if (action.equals("add")) {
				carrello.addOrRemoveQuantity(1, product);
			} else if (action.equals("remove")) {
				carrello.addOrRemoveQuantity(-1, product);
			} 
		}else {
			req.getSession().setAttribute("errore", "Errore nel carrello");
			resp.sendRedirect(req.getContextPath() + "/error.jsp");
		}
		resp.sendRedirect(req.getContextPath() + "/carrello.jsp");
		return;
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
	
	

}
