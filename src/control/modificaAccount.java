package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.UserBean;
import model.UserDAO;

@WebServlet("/modificaAccount")
public class modificaAccount extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String nome = req.getParameter("nome");
		String cognome = req.getParameter("cognome");
		String CF = req.getParameter("cf");
		String telefono = req.getParameter("telefono");
		
		if (email == null || nome == null || cognome == null || CF == null || telefono == null) {
			req.getSession().setAttribute("errore", "Errore nella modifica dell'Account");
			resp.sendRedirect(req.getContextPath() + "/error.jsp");
		}
		UserBean user = new UserBean();
		user = (UserBean) req.getSession().getAttribute("user");
		
		user.setEmail(email);
		user.setNome(nome);
		user.setCognome(cognome);
		user.setTelefono(telefono);
		user.setCf(CF);
		
		try {
			UserDAO.doUpdateAccount(user);
			user = UserDAO.findByUsername(email);
		} catch (SQLException e) {
			req.getSession().setAttribute("errore", "Errore nella modifica dell'Account");
			resp.sendRedirect(req.getContextPath() + "/error.jsp");
			}
		
		
		
		req.getSession().setAttribute("user", user);
		resp.sendRedirect(req.getContextPath() + "/account.jsp");
	}
	
	
}
