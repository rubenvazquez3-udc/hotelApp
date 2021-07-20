package es.udc.hotelapp.backend.rest.dtos;

public class ProductDto {

	private Long id;
	private String name;
	private String description;
	private Double price;
	private HotelDto hotel;

	public ProductDto() {}

	public ProductDto(Long id, String name, String description, Double price, HotelDto hotel) {
		this.id = id;
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

	public HotelDto getHotel() {
		return hotel;
	}

	public void setHotel(HotelDto hotel) {
		this.hotel = hotel;
	}

}
