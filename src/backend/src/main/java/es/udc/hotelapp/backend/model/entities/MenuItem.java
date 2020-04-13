package es.udc.hotelapp.backend.model.entities;

import java.math.BigDecimal;
import java.util.Set;

public class MenuItem  extends AccountItem {
	
	private Set<ProductItem> items;

	public Set<ProductItem> getItems() {
		return items;
	}

	public void setItems(Set<ProductItem> items) {
		this.items = items;
	}

	public MenuItem() {}

	public MenuItem( Account account, int quantity, BigDecimal itemPrice, Set<ProductItem> items) {
		super(account, quantity, itemPrice);
		this.items = items;
	}
	
	

}
