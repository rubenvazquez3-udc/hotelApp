package es.udc.hotelapp.backend.model.entities;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;


@Entity
public class AccountItem {
	
	private Long id;
	private Account account;
	private int quantity;
	private BigDecimal itemPrice;
	
	
	public AccountItem() {	}

	public AccountItem( Account account, int quantity, BigDecimal itemPrice) {
		this.account = account;
		this.quantity = quantity;
		this.itemPrice = itemPrice;
	}
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@ManyToOne(optional=false, fetch=FetchType.LAZY)
	@JoinColumn(name="accountId")
	
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
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
	
	
	@Transient
	public BigDecimal getTotalPrice() {
		return this.getItemPrice().multiply(new BigDecimal(this.getQuantity()));
	}
	

}
