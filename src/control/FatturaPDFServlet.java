package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import model.FatturaBean;
import model.FatturaDAO;
import model.ProductCart;
import model.UserBean;
import model.UserDAO;

@WebServlet("/fatturaPDF")
public class FatturaPDFServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer codiceFattura = Integer.parseInt(req.getParameter("codiceFattura"));
		Integer idOrdine = Integer.parseInt(req.getParameter("idOrdine"));
		
		FatturaBean bean = FatturaDAO.doRetrieveByCode(codiceFattura);
		List<ProductCart> products = bean.getProdotti_fatturati();
		
		
		UserBean user = new UserBean();
		try {
			user = UserDAO.findByIdOrdine(idOrdine);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		resp.setContentType("application/pdf");
		Document pdf = new Document();
		try {
			@SuppressWarnings("unused")
			PdfWriter writer = PdfWriter.getInstance(pdf, resp.getOutputStream());
			pdf.open();
			
			
			Paragraph head = new Paragraph();
			head.add("HUB PROTEIN");
			head.setAlignment(Element.ALIGN_CENTER);
			pdf.add(head);
			
			Paragraph cliente = new Paragraph();
			cliente.add("Grazie per l'acquisto " + user.getNome() + " " + user.getCognome());
			cliente.setMultipliedLeading(5);
			pdf.add(cliente);
			
			Paragraph spazio = new Paragraph("-");
			spazio.setMultipliedLeading(7);
			pdf.add(spazio);
			
			PdfPTable table = new PdfPTable(6);
			table.addCell(new Paragraph("Codice"));
			table.addCell(new Paragraph("Gusto/Colore"));
			table.addCell(new Paragraph("Formato"));
			table.addCell(new Paragraph("Quantità"));
			table.addCell(new Paragraph("Iva"));
			table.addCell(new Paragraph("Prezzo"));
			
			for (ProductCart productCart : products) {
				Double iva = productCart.getProduct().getIva();
				Double prezzo = productCart.getProduct().getPrezzo();
				Integer codice = productCart.getProduct().getCodice();
				Integer quantita = productCart.getQuantita();
				table.addCell(new Paragraph(codice.toString()));
				table.addCell(new Paragraph(productCart.getProduct().getGusto_colore()));
				table.addCell(new Paragraph(productCart.getProduct().getFormato()));
				table.addCell(new Paragraph(quantita.toString()));
				table.addCell(new Paragraph(iva.toString()));
				table.addCell(new Paragraph(prezzo.toString()));
			}
			
			table.setWidthPercentage(95);
			pdf.add(table);
			
			Paragraph totale = new Paragraph();
			Double importo = bean.getImporto();
			totale.add("Totale: " + importo.toString());
			totale.setMultipliedLeading(4);
			pdf.add(totale);
			
			Paragraph metodoPagamento = new Paragraph();
			metodoPagamento.add("Metodo pagamento: " + bean.getMetodo_pagamento());
			metodoPagamento.setMultipliedLeading(2);
			pdf.add(metodoPagamento);
			
			pdf.close();
		} catch (DocumentException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(bean);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
	
	
}
