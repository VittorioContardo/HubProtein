package control;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Carrello;
import model.FatturaBean;
import model.FatturaDAO;
import model.OrdineBean;
import model.OrdineDAO;
import model.ProductDAO;
import model.SpedizioneBean;
import model.SpedizioneDAO;

@WebServlet("/completaAcquisto")
public class completaAcquistoServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer indirizzo = Integer.parseInt(req.getParameter("indirizzo"));
		Integer sped = Integer.parseInt(req.getParameter("spedizione"));
		String metodo_pagamento = req.getParameter("metodo_pagamento");
		String utente = req.getParameter("user");
		Carrello carrello = (Carrello) req.getSession().getAttribute("carrello");
		
		if (carrello == null || indirizzo == null || sped == null || metodo_pagamento == null || utente == null) {
			resp.sendRedirect(req.getContextPath() + "/error.jsp");
			return;
		}
		 
		Date date = new Date(System.currentTimeMillis());
		OrdineBean ordine = new OrdineBean(0, date, "Nuovo", sped, utente, indirizzo, carrello.getProducts());
		try {
			int idOrdine, idFattura;
			SpedizioneBean spedizione = SpedizioneDAO.doRetrieveByCode(sped);
			//inserimento ordine
			OrdineDAO.doSave(ordine);
			idOrdine = OrdineDAO.getLastNumOrdine();
			System.out.println(idOrdine);
			ordine.setId(idOrdine);
			
			//inserimento prodotti ordine
			OrdineDAO.insertProduct(ordine);
			
			FatturaBean fattura = new FatturaBean(0, carrello.getTotal()+spedizione.getCosto(), metodo_pagamento, date, idOrdine, carrello.getProducts());
			//inserimento fattura
			FatturaDAO.doSave(fattura);
			idFattura = FatturaDAO.getLastNumFattura();
			System.out.println(idFattura);
			fattura.setIdFattura(idFattura);
			//inserimento prodotti fattura
			FatturaDAO.insertProduct(fattura);
			ProductDAO.setQuantita(ordine);
			carrello.svuotaCarrello();
		} catch (SQLException e) {
			req.getSession().setAttribute("errore", "Errore nell'inserimento nel carrello!");
			resp.sendRedirect(req.getContextPath() + "/error.jsp");
			e.printStackTrace();
		}
		System.out.println("ok");
		resp.sendRedirect(req.getContextPath() + "/succesAcquisto.jsp");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
}