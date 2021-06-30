package es.udc.hotelapp.backend.rest.dtos;

import java.util.List;

public class HotelDto {

	private Long id;
	private String name;
	private String manager;
	private String address;
	private String phoneNumber;
	private String description;
	private List<RoomTypePriceDto> prices;
	

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

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	

	public HotelDto(Long id, String name, String manager, String address, String phoneNumber, String description, List<RoomTypePriceDto> items) {
		this.id = id;
		this.name = name;
		this.manager = manager;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.description = description;
		this.prices = items;
	}

	public HotelDto() {
	}

	public List<RoomTypePriceDto> getPrices() {
		return prices;
	}

	public void setPrices(List<RoomTypePriceDto> prices) {
		this.prices = prices;
	}

}
