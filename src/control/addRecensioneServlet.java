package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.RecensioneBean;
import model.RecensioneDAO;

@WebServlet("/addRecensione")
public class addRecensioneServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		Integer codiceProdotto = Integer.parseInt(req.getParameter("codiceProdotto"));
		String commento = req.getParameter("commento");
		Double valore = Double.parseDouble(req.getParameter("rating"));
		
		//System.out.println(email + codiceProdotto + commento + valore);
		if(email == null || codiceProdotto == null || commento == null || valore == null) {
			req.getSession().setAttribute("errore", "Errore inserimento recensione");
			resp.sendRedirect(req.getContextPath() + "/error.jsp");
		}
		
		RecensioneBean recensione = new RecensioneBean(codiceProdotto, email, valore, commento);
		try {
			RecensioneDAO.doSave(recensione);
		} catch (SQLException e) {
			req.getSession().setAttribute("errore", "Errore inserimento recensione");
			resp.sendRedirect(req.getContextPath() + "/error.jsp");
			e.printStackTrace();
		}
		req.getSession().removeAttribute("recensioni");
		try {
			req.getSession().setAttribute("recensioni", RecensioneDAO.doRetrieveByCode(codiceProdotto));
		} catch (SQLException e) {
			req.getSession().setAttribute("errore", "Errore inserimento recensione");
			resp.sendRedirect(req.getContextPath() + "/error.jsp");
			e.printStackTrace();
		}
		resp.sendRedirect(req.getContextPath() + "/dettaglio-prodotto.jsp");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
	
	
}
