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

@WebServlet("/checkRegisteredUser")
public class checkRegisteredUserServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("emailValue");
		
		System.out.println(email);
		Boolean isRegistered = false;
		try {
			UserBean user = UserDAO.findByUsername(email);
			if (user.getEmail() != null) {
				isRegistered = true;
			}
		} catch (SQLException e) {
			req.getSession().setAttribute("errore", "Errore nella registrazione");
			resp.sendRedirect(req.getContextPath() + "/error.jsp");
			e.printStackTrace();
		}
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		
		String json = "{\"registered\" : " + isRegistered + "}";
		
		resp.getWriter().write(json);
		System.out.println(json);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
	
}
