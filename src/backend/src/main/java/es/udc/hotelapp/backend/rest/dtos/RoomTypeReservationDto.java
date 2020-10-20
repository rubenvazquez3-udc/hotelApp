package es.udc.hotelapp.backend.rest.dtos;

import java.time.LocalDate;

public class RoomTypeReservationDto {

	private Long id;
	private UserDto user;
	private LocalDate inbound;
	private LocalDate outbound;
	private int rooms;
	private RoomTypeDto roomtype;
	private HotelDto hotel;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserDto getUser() {
		return user;
	}


	public void setUser(UserDto user) {
		this.user = user;
	}



	public LocalDate getInbound() {
		return inbound;
	}


	public void setInbound(LocalDate inbound) {
		this.inbound = inbound;
	}



	public LocalDate getOutbound() {
		return outbound;
	}



	public void setOutbound(LocalDate outbound) {
		this.outbound = outbound;
	}


	public int getRooms() {
		return rooms;
	}

	public void setRooms(int rooms) {
		this.rooms = rooms;
	}

	public RoomTypeDto getRoomtype() {
		return roomtype;
	}

	public void setRoomtype(RoomTypeDto roomtype) {
		this.roomtype = roomtype;
	}

	public HotelDto getHotel() {
		return hotel;
	}

	public void setHotel(HotelDto hotel) {
		this.hotel = hotel;
	}

	
	public RoomTypeReservationDto(Long id, UserDto user, LocalDate inbound, LocalDate outbound, int rooms,
			RoomTypeDto roomtype, HotelDto hotel) {
		this.id = id;
		this.user = user;
		this.inbound = inbound;
		this.outbound = outbound;
		this.rooms = rooms;
		this.roomtype = roomtype;
		this.hotel = hotel;
	}

	public RoomTypeReservationDto() {
	}

}
