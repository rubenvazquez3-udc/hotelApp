package es.udc.hotelapp.backend.rest.dtos;

public class GuestReservationDto {

	private Long id;
	private RoomTypeReservationDto reservation;
	private GuestDto guest;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public RoomTypeReservationDto getReservation() {
		return reservation;
	}

	public void setReservation(RoomTypeReservationDto reservation) {
		this.reservation = reservation;
	}

	public GuestDto getGuest() {
		return guest;
	}

	public void setGuest(GuestDto guest) {
		this.guest = guest;
	}

	public GuestReservationDto(Long id, RoomTypeReservationDto reservation, GuestDto guest) {
		this.id = id;
		this.reservation = reservation;
		this.guest = guest;
	}

	public GuestReservationDto() {
	}

}
