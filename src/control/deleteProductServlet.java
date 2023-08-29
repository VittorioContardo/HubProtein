package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ProductDAO;

@WebServlet("/deleteProduct")
public class deleteProductServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer codice = Integer.parseInt(req.getParameter("codice"));
		String formato = req.getParameter("formato");
		String gusto_colore = req.getParameter("gusto_colore");
		
		if (codice != null && formato != null && gusto_colore != null) {
			try {
				ProductDAO.doDelete(codice, formato, gusto_colore);
				System.out.println("ok");

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
