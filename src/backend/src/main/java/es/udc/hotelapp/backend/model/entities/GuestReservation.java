package es.udc.hotelapp.backend.model.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class GuestReservation {
	
	private Long id;
	private Reservation reservation;
	private Guest guest;
	
	public GuestReservation() {	}
	
	
	public GuestReservation(Reservation reservation, Guest guest) {

		this.reservation = reservation;
		this.guest = guest;
	}


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@ManyToOne(optional=false, fetch= FetchType.LAZY)
	@JoinColumn(name="reservationId")
	public Reservation getReservation() {
		return reservation;
	}
	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}
	@ManyToOne(optional=false, fetch= FetchType.LAZY)
	@JoinColumn(name="guestId")
	public Guest getGuest() {
		return guest;
	}
	public void setGuest(Guest guest) {
		this.guest = guest;
	}
	
	

}
