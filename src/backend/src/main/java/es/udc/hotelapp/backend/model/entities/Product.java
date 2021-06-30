package es.udc.hotelapp.backend.model.entities;



import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class Product {
	
	private Long id;
	private String name;
	private String description;
	private Double price;
	private Hotel hotel;
	
	
	public Product() {	}


	public Product(String name, String description, Double price, Hotel hotel) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.hotel= hotel;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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


	@ManyToOne(optional=false, fetch=FetchType.LAZY)
	@JoinColumn(name="hotelid")
	public Hotel getHotel() {
		return hotel;
	}


	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}
	
	

}
