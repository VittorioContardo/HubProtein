package model;

import java.io.Serializable;

public class ProductCart implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private ProductBean product;
	private int quantità;
	public ProductCart(ProductBean product, int quantita) {
		super();
		this.product = product;
		this.quantità = quantita;
	}
	public ProductBean getProduct() {
		return product;
	}
	public void setProduct(ProductBean product) {
		this.product = product;
	}
	public int getQuantita() {
		return quantità;
	}
	public void setQuantita(int quantita) {
		this.quantità = quantita;
	}
	@Override
	public String toString() {
		return "ProductCart [product=" + product + ", quantità=" + quantità + "]";
	}
}
