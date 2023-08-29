package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.OrdineBean;
import model.OrdineDAO;

@WebServlet("/dettaglioOrdine")
public class dettaglioOrdineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer idOrdine = Integer.parseInt(req.getParameter("idOrdine"));
		
		OrdineBean ordine = new OrdineBean();
		try {
			ordine = OrdineDAO.getOrdineById(idOrdine);
		} catch (SQLException e) {
			req.getSession().setAttribute("errore", "Errore nel dettaglio dell'Ordine");
			resp.sendRedirect(req.getContextPath() + "/error.jsp");
			e.printStackTrace();
		}
		req.getSession().setAttribute("ordine", ordine);
		resp.sendRedirect(req.getContextPath() + "/dettaglio-ordine.jsp");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
	
	
}
