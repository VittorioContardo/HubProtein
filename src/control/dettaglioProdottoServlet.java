package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ProductBean;
import model.ProductDAO;

@WebServlet("/dettaglio")
public class dettaglioProdottoServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String codice = req.getParameter("codice");
		String formato = req.getParameter("formato");
		String gusto_colore = req.getParameter("gusto_colore");
		
		//System.out.println(codice + " " + formato + " " + gusto_colore);
		
		if (codice == null || formato == null || gusto_colore == null) {
			System.out.println("Errore nel dettaglio del Prodotto");
			resp.sendRedirect(req.getContextPath() + "/error.jsp");
			return;
		}
		try {
			ProductBean productBean = ProductDAO.doRetrieveByFullCode(Integer.parseInt(codice), formato, gusto_colore);
			//System.out.println(productBean);
 			List<ProductBean> list = ProductDAO.doRetrieveByKey(Integer.parseInt(codice));
			List<String> formati = ProductDAO.getFormatiByCodeGusto(Integer.parseInt(codice), gusto_colore);
			List<String> gusti_colori = list.stream().map(o -> o.getGusto_colore()).distinct().collect(Collectors.toList());
			if (productBean.getCodice() == -1) {
				productBean = ProductDAO.doRetrieveByFullCode(Integer.parseInt(codice), formati.get(0), gusto_colore);
			}
			req.getSession().setAttribute("gusti_colori", gusti_colori);
			req.getSession().setAttribute("formati", formati);
			req.getSession().setAttribute("product", productBean);
		} catch (NumberFormatException | SQLException e) {
			req.getSession().setAttribute("errore", "Errore nel dettaglio del Prodotto");
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
