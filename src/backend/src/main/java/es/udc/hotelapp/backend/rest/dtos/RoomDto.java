package es.udc.hotelapp.backend.rest.dtos;

public class RoomDto {
	private Long id;
	private int number;
	private String status;
	private RoomTypeDto type;
	private HotelDto hotel;

	public RoomDto(Long id, int number, String status, RoomTypeDto type, HotelDto hotel) {
		this.id = id;
		this.number = number;
		this.status = status;
		this.type = type;
		this.hotel = hotel;
	}

	public RoomDto() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public RoomTypeDto getType() {
		return type;
	}

	public void setType(RoomTypeDto type) {
		this.type = type;
	}

	public HotelDto getHotel() {
		return hotel;
	}

	public void setHotel(HotelDto hotel) {
		this.hotel = hotel;
	}

}
