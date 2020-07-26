package es.udc.hotelapp.backend.rest.dtos;

public class RoomTypeReservationDto {

	private Long id;
	private ReservationDto reservation;
	private int rooms;
	private RoomTypeDto roomtype;
	private HotelDto hotel;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ReservationDto getReservation() {
		return reservation;
	}

	public void setReservation(ReservationDto reservation) {
		this.reservation = reservation;
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

	public RoomTypeReservationDto(Long id, ReservationDto reservation, int rooms, RoomTypeDto roomtype,
			HotelDto hotel) {
		this.id = id;
		this.reservation = reservation;
		this.rooms = rooms;
		this.roomtype = roomtype;
		this.hotel = hotel;
	}

	public RoomTypeReservationDto() {
	}

}
