package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.OrdineDAO;
import model.ProductBean;
import model.ProductDAO;
import model.UserBean;
import model.UserDAO;

@WebServlet("/login")
public class loginServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		String redirectedPage;
		boolean isLogged = false; 
		UserBean user = null;
		try {
			user = UserDAO.findByUsername(email);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//System.out.println(user.toString());
		if (checkUser(user, email, password)) {
			req.getSession().setAttribute("user", user);
			isLogged = true;
			req.getSession().setAttribute("isLogged", isLogged);
			redirectedPage = "/index.jsp";
		} else {
			isLogged = false;
			req.getSession().setAttribute("isLogged", isLogged);
			redirectedPage = "/login.jsp";
		}
		
		try {
			Collection<ProductBean> products = ProductDAO.doRetrieveAll();
			req.getSession().setAttribute("ordini", OrdineDAO.getOrdiniByUser(user.getEmail()));
			req.getSession().setAttribute("products", products);
		} catch (SQLException e) {
			req.getSession().setAttribute("errore", "Errore nel Login");
			resp.sendRedirect(req.getContextPath() + "/error.jsp");
			e.printStackTrace();
		}
		
		resp.sendRedirect(req.getContextPath() + redirectedPage);
	}
	
	private static boolean checkUser(UserBean user, String email, String password) {
		if (user != null) {
			if (user.getEmail() != null && user.getPassword() != null) {
				if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
					return true;
				} else return false;
			} else return false;
		} else return false;

		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}
}
