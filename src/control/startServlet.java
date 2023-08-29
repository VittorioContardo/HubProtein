package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Carrello;
import model.ProductBean;
import model.ProductDAO;
@WebServlet("/start")
public class startServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Carrello cart = (Carrello) req.getSession().getAttribute("carrello");
		if (cart == null) {
			cart = new Carrello();
			req.getSession().setAttribute("carrello", cart);
		}
		List<ProductBean> products = (List<ProductBean>) req.getSession().getAttribute("products");
		if (products == null) {
			try {
				products = ProductDAO.doRetrieveAll();
			} catch (SQLException e) {
				req.getSession().setAttribute("errore", "Errore");
				resp.sendRedirect(req.getContextPath() + "/error.jsp");
				e.printStackTrace();
			}
			req.getSession().setAttribute("products", products);
		}
		resp.sendRedirect(req.getContextPath() + "/index.jsp");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
	
	
}
