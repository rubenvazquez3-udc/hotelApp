package es.udc.hotelapp.backend.model.entities;

public class GuestReservation {
	
	private Long id;
	private Reservation reservation;
	private Guest guest;
	
	public GuestReservation() {	}
	
	
	public GuestReservation(Reservation reservation, Guest guest) {

		this.reservation = reservation;
		this.guest = guest;
	}


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Reservation getReservation() {
		return reservation;
	}
	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}
	public Guest getGuest() {
		return guest;
	}
	public void setGuest(Guest guest) {
		this.guest = guest;
	}
	
	

}
