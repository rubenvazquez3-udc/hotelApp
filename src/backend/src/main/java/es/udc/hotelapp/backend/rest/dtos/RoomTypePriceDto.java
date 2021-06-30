package es.udc.hotelapp.backend.rest.dtos;

import java.math.BigDecimal;

public class RoomTypePriceDto {
	
	private Long id;
	private HotelDto hotel;
	private RoomTypeDto type;
	private BigDecimal price;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public HotelDto getHotel() {
		return hotel;
	}
	public void setHotel(HotelDto hotel) {
		this.hotel = hotel;
	}
	public RoomTypeDto getType() {
		return type;
	}
	public void setType(RoomTypeDto type) {
		this.type = type;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public RoomTypePriceDto(Long id, HotelDto hotel, RoomTypeDto type, BigDecimal price) {
		this.id = id;
		this.hotel = hotel;
		this.type = type;
		this.price = price;
	}
	public RoomTypePriceDto() {	}
	
	
	

}
