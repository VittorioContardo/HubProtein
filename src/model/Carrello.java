package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Carrello {
	private List<ProductCart> products = new ArrayList<>();

	public Carrello() {
		super();
	}
	
	public void addToCart(ProductBean product, int quantità) {
		ProductCart bean = new ProductCart(product, quantità);

		for (Iterator<ProductCart> iterator = products.iterator(); iterator.hasNext();) {
			ProductCart productCart = (ProductCart) iterator.next();
			if (productCart.getProduct().equals(bean.getProduct())) {
				productCart.setQuantita(productCart.getQuantita() + quantità);
				return;
			}
			
		}
		products.add(new ProductCart(product, quantità));
	}
	
	public double getTotal() {
		double total = 0;
	
		for (Iterator<ProductCart> iterator = products.iterator(); iterator.hasNext();) {
			ProductCart productCart = (ProductCart) iterator.next();
			total += productCart.getProduct().getPrezzo() * productCart.getQuantita();
		}
		return total;
	}
	
	public void removeToCart(ProductBean product) {
		int index = 0;
		for (Iterator<ProductCart> iterator = products.iterator(); iterator.hasNext();) {
			ProductCart productCart = (ProductCart) iterator.next();
			if (!(product.equals(productCart.getProduct())) ) {
				index++;
			} else break;
		}
		//System.out.println(index);
		products.remove(index);
	}

	public List<ProductCart> getProducts() {
		return products;
	}

	public void setProducts(List<ProductCart> products) {
		this.products = products;
	}
	
	public void addOrRemoveQuantity(int value, ProductBean product) {
		for (Iterator<ProductCart> iterator = products.iterator(); iterator.hasNext();) {
			ProductCart productCart = (ProductCart) iterator.next();
			if (productCart.getProduct().equals(product)) {
					productCart.setQuantita(productCart.getQuantita() + value);
					if (productCart.getQuantita() <= 0) {
						this.removeToCart(product);
						break;
					}
			}
		}
	}

	@Override
	public String toString() {
		return "Carrello [products=" + products + "]";
	}
	
	public void svuotaCarrello() {
		products = new ArrayList<ProductCart>();
	}
}
