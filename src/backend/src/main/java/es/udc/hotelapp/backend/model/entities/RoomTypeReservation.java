package es.udc.hotelapp.backend.model.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class RoomTypeReservation {
	
	private Long id;
	private Reservation reservation;
	private int rooms;
	private RoomType roomtype;
	
	public RoomTypeReservation() {	}

	public RoomTypeReservation(Reservation reservation, int rooms, RoomType roomtype) {
		this.reservation = reservation;
		this.rooms = rooms;
		this.roomtype = roomtype;
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

	public int getRooms() {
		return rooms;
	}

	public void setRooms(int rooms) {
		this.rooms = rooms;
	}
	@ManyToOne(optional=false, fetch= FetchType.LAZY)
	@JoinColumn(name="typeId")
	public RoomType getRoomtype() {
		return roomtype;
	}

	public void setRoomtype(RoomType roomtype) {
		this.roomtype = roomtype;
	}
	
	

}
