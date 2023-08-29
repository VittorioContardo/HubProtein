package control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ProductBean;
import model.ProductDAO;

@WebServlet("/search")
public class searchbarServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String param = req.getParameter("param");
		
		//System.out.println(param.length());
		List<ProductBean> product = ProductDAO.searchByString(param);
		
		//System.out.println(product);
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		
		String json = "{\"suggestions\": [";
		if(param.length() != 0) {
			
			for (int i = 0; i < product.size(); i++) {
				if (i == product.size()-1) {
					json += "\"" + product.get(i).getNome() + "\"";
				} else {
					json += "\"" + product.get(i).getNome() + "\",";
				}
			}
		}
		json = json + "] }";
		
		
		resp.getWriter().write(json);
		//System.out.println(json);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
	
	
}
