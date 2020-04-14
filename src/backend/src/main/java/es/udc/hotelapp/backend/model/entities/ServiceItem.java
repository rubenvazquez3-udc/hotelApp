package es.udc.hotelapp.backend.model.entities;

import java.math.BigDecimal;

public class ServiceItem  extends AccountItem{
	private Service service;
	
	

	public ServiceItem() {
	
	}

	public ServiceItem(Account account, int quantity, BigDecimal itemPrice, Service service) {
		super(account, quantity, itemPrice);
		this.service = service;
		}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}
	
	

}
