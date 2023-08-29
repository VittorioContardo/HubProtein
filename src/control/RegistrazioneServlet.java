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

@WebServlet("/registrazione")
public class RegistrazioneServlet extends HttpServlet{
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
		String password = req.getParameter("password");
		String telefono = req.getParameter("telefono");
		
		if (email == null || nome == null || cognome == null || CF == null || password == null || telefono == null) {
			req.getSession().setAttribute("errore", "Errore nella registrazione");
			resp.sendRedirect(req.getContextPath() + "/error.jsp");
		}
		
		UserBean user = new UserBean(email, password, CF, nome, cognome, telefono, false);
		try {
			UserDAO.registerUser(user);
		} catch (SQLException e) {
			req.getSession().setAttribute("errore", "Errore nella registrazione");
			resp.sendRedirect(req.getContextPath() + "/error.jsp");
			e.printStackTrace();
		}
		
		
		req.getSession().setAttribute("user", user);
		resp.sendRedirect(req.getContextPath() + "/index.jsp");
	}
	
	

}
