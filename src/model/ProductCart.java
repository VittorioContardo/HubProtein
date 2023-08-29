package model;

import java.io.Serializable;

public class ProductCart implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private ProductBean product;
	private int quantit�;
	public ProductCart(ProductBean product, int quantita) {
		super();
		this.product = product;
		this.quantit� = quantita;
	}
	public ProductBean getProduct() {
		return product;
	}
	public void setProduct(ProductBean product) {
		this.product = product;
	}
	public int getQuantita() {
		return quantit�;
	}
	public void setQuantita(int quantita) {
		this.quantit� = quantita;
	}
	@Override
	public String toString() {
		return "ProductCart [product=" + product + ", quantit�=" + quantit� + "]";
	}
}
