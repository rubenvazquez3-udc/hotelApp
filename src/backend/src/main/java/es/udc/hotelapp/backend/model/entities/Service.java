package es.udc.hotelapp.backend.model.entities;

public class Service {
	
	private Long id;
	private String name;
	private String description;
	private Double price;
	private Hotel hotel;
	
	public Service() {}
	
	public Service(String name, String description, Double price, Hotel hotel) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.hotel = hotel;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Hotel getHotel() {
		return hotel;
	}
	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}
	
	

}
