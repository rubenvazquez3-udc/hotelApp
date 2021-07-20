package es.udc.hotelapp.backend.rest.dtos;

import java.math.BigDecimal;

public class AccountItemDto {

	private Long id;
	private String name;
	private int quantity;
	private BigDecimal itemPrice;

	public AccountItemDto() {}

	public AccountItemDto(Long id, String name, int quantity, BigDecimal itemPrice) {
		this.id = id;
		this.name = name;
		this.quantity = quantity;
		this.itemPrice = itemPrice;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(BigDecimal itemPrice) {
		this.itemPrice = itemPrice;
	}

}
