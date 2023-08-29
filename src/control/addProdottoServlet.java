package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ProductBean;
import model.ProductDAO;

@WebServlet("/addProdotto")
public class addProdottoServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer codice = Integer.parseInt(req.getParameter("codice"));
		String formato = req.getParameter("formato");
		String gusto_colore = req.getParameter("gusto_colore");
		String nome = req.getParameter("nome");
		String descrizione = req.getParameter("descrizione");
		Integer quantita = Integer.parseInt(req.getParameter("quantita"));
		Double prezzo = Double.parseDouble(req.getParameter("prezzo"));
		Double iva = Double.parseDouble(req.getParameter("iva"));
		String vegan = req.getParameter("vegan");
		Integer categoria = Integer.parseInt(req.getParameter("categoria"));
		String immagine = req.getParameter("immagine");
		
		if (codice == null || formato == null || gusto_colore == null || nome == null || descrizione == null || quantita == null
				|| prezzo == null || iva == null || categoria == null || immagine == null) {
			req.getSession().setAttribute("errore", "Errore nell'inserimento del prodotto! Parametri non corretti");
			resp.sendRedirect(req.getContextPath() + "/error.jsp");
		}
		
		
		boolean isVegan;
		if (vegan == null) isVegan = false; else isVegan = true;
		
		ProductBean bean = new ProductBean(codice, formato, gusto_colore, nome, descrizione, quantita, prezzo, iva, isVegan, categoria, immagine);
		System.out.println(bean.toString());
		if (categoria == 5 && formato.equals("tutti")) {
			try {
				ProductDAO.doSave(new ProductBean(codice, "S", gusto_colore, nome, descrizione, quantita, prezzo, iva, isVegan, categoria, immagine));
				ProductDAO.doSave(new ProductBean(codice, "M", gusto_colore, nome, descrizione, quantita, prezzo, iva, isVegan, categoria, immagine));
				ProductDAO.doSave(new ProductBean(codice, "L", gusto_colore, nome, descrizione, quantita, prezzo, iva, isVegan, categoria, immagine));
			} catch (SQLException e) {
				req.getSession().setAttribute("inserted", false);
				req.getSession().setAttribute("errore", "Errore nell'inserimento del prodotto!");
				resp.sendRedirect(req.getContextPath() + "/error.jsp");
				e.printStackTrace();
			}
		} else if (categoria == 4 && formato.equals("tutti")) {
			try {
				ProductDAO.doSave(new ProductBean(codice, "S", gusto_colore, nome, descrizione, quantita, prezzo, iva, isVegan, categoria, immagine));
				ProductDAO.doSave(new ProductBean(codice, "M", gusto_colore, nome, descrizione, quantita, prezzo, iva, isVegan, categoria, immagine));
				ProductDAO.doSave(new ProductBean(codice, "L", gusto_colore, nome, descrizione, quantita, prezzo, iva, isVegan, categoria, immagine));
				ProductDAO.doSave(new ProductBean(codice, "XL", gusto_colore, nome, descrizione, quantita, prezzo, iva, isVegan, categoria, immagine));
			} catch (SQLException e) {
				req.getSession().setAttribute("inserted", false);
				req.getSession().setAttribute("errore", "Errore nell'inserimento del prodotto!");
				resp.sendRedirect(req.getContextPath() + "/error.jsp");
				e.printStackTrace();
			}
		} else {
			try {
				ProductDAO.doSave(bean);
			} catch (SQLException e) {
				req.getSession().setAttribute("inserted", false);
				req.getSession().setAttribute("errore", "Errore nell'inserimento del prodotto!");
				resp.sendRedirect(req.getContextPath() + "/error.jsp");
				e.printStackTrace();
			}
		}
		/*switch (categoria) {
			case 1:
				try {
					ProductDAO.doSave(new ProductBean(codice, formato, gusto_colore, nome, descrizione, quantita, prezzo, iva, isVegan, categoria, immagine));
				} catch (SQLException e) {
					req.getSession().setAttribute("errore", "Errore nell'inserimento del prodotto!");
					resp.sendRedirect(req.getContextPath() + "/error.jsp");
					e.printStackTrace();
				}
				break;
				
			
			case 2:
				try {
					ProductDAO.doSave(new ProductBean(codice, formato, gusto_colore, nome, descrizione, quantita, prezzo, iva, isVegan, categoria, immagine));
				} catch (SQLException e1) {
					req.getSession().setAttribute("errore", "Errore nell'inserimento del prodotto!");
					resp.sendRedirect(req.getContextPath() + "/error.jsp");
					e1.printStackTrace();
				}
				break;
				
			case 3:
				try {
					ProductDAO.doSave(new ProductBean(codice, formato, gusto_colore, nome, descrizione, quantita, prezzo, iva, isVegan, categoria, immagine));
				} catch (SQLException e1) {
					req.getSession().setAttribute("errore", "Errore nell'inserimento del prodotto!");
					resp.sendRedirect(req.getContextPath() + "/error.jsp");
					e1.printStackTrace();
				}
				break;
				
			case 4:
				if (formato.equals("tutti")) {
					try {
						ProductDAO.doSave(new ProductBean(codice, "S", gusto_colore, nome, descrizione, quantita, prezzo, iva, isVegan, categoria, immagine));
						ProductDAO.doSave(new ProductBean(codice, "M", gusto_colore, nome, descrizione, quantita, prezzo, iva, isVegan, categoria, immagine));
						ProductDAO.doSave(new ProductBean(codice, "L", gusto_colore, nome, descrizione, quantita, prezzo, iva, isVegan, categoria, immagine));
						ProductDAO.doSave(new ProductBean(codice, "XL", gusto_colore, nome, descrizione, quantita, prezzo, iva, isVegan, categoria, immagine));
					} catch (SQLException e) {
						req.getSession().setAttribute("errore", "Errore nell'inserimento del prodotto!");
						resp.sendRedirect(req.getContextPath() + "/error.jsp");
						e.printStackTrace();
					}
				} else {
					try {
						ProductDAO.doSave(new ProductBean(codice, formato, gusto_colore, nome, descrizione, quantita, prezzo, iva, isVegan, categoria, immagine));
					} catch (SQLException e) {
						req.getSession().setAttribute("errore", "Errore nell'inserimento del prodotto!");
						resp.sendRedirect(req.getContextPath() + "/error.jsp");
						e.printStackTrace();
					}
				}
				break;
			case 5:
				if (formato.equals("tutti")) {
					try {
						ProductDAO.doSave(new ProductBean(codice, "S", gusto_colore, nome, descrizione, quantita, prezzo, iva, isVegan, categoria, immagine));
						ProductDAO.doSave(new ProductBean(codice, "M", gusto_colore, nome, descrizione, quantita, prezzo, iva, isVegan, categoria, immagine));
						ProductDAO.doSave(new ProductBean(codice, "L", gusto_colore, nome, descrizione, quantita, prezzo, iva, isVegan, categoria, immagine));
					} catch (SQLException e) {
						req.getSession().setAttribute("errore", "Errore nell'inserimento del prodotto!");
						resp.sendRedirect(req.getContextPath() + "/error.jsp");
						e.printStackTrace();
					}
				} else {
					try {
						ProductDAO.doSave(new ProductBean(codice, formato, gusto_colore, nome, descrizione, quantita, prezzo, iva, isVegan, categoria, immagine));
					} catch (SQLException e) {
						req.getSession().setAttribute("inserted", false);
						req.getSession().setAttribute("errore", "Errore nell'inserimento del prodotto!");
						resp.sendRedirect(req.getContextPath() + "/error.jsp");
						e.printStackTrace();
					}
				}
				break;
		}*/
		req.getSession().removeAttribute("products");
		try {
			req.getSession().setAttribute("products", ProductDAO.doRetrieveAll());
		} catch (SQLException e) {
			req.getSession().setAttribute("errore", "Errore nell'inserimento del prodotto!");
			e.printStackTrace();
		}
		req.getSession().setAttribute("inserted", true);
		resp.sendRedirect(req.getContextPath() + "/AddProdotto.jsp");
		return;
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
	
}
