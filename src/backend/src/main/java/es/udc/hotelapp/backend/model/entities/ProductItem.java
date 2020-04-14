package es.udc.hotelapp.backend.model.entities;

import java.math.BigDecimal;
import java.util.Set;



public class ProductItem extends AccountItem{
	
	private Set<Product> products;

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	public ProductItem() {	}

	public ProductItem(Account account, int quantity, BigDecimal itemPrice, Set<Product> products) {
		super(account, quantity, itemPrice);
		this.products = products;
	}
	
	
	

}
