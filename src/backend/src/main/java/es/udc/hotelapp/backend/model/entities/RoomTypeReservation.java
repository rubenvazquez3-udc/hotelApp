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
	private Hotel hotel;
	
	public RoomTypeReservation() {	}

	public RoomTypeReservation(Reservation reservation, int rooms, RoomType roomtype, Hotel hotel) {
		this.reservation = reservation;
		this.rooms = rooms;
		this.roomtype = roomtype;
		this.hotel = hotel;
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

	@ManyToOne(optional=false, fetch= FetchType.LAZY)
	@JoinColumn(name="hotelId")
	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}
	
	

}
