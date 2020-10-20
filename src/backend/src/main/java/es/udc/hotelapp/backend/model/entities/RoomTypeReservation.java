package es.udc.hotelapp.backend.model.entities;

import java.time.LocalDate;

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
	private LocalDate inbound;
	private LocalDate outbound;
	private User user;
	private int rooms;
	private RoomType roomtype;
	private Hotel hotel;
	
	public RoomTypeReservation() {	}

	

	public RoomTypeReservation(User user, LocalDate inbound, LocalDate outbound, int rooms, RoomType roomtype, Hotel hotel) {
		this.user= user;
		this.inbound = inbound;
		this.outbound = outbound;
		this.rooms = rooms;
		this.roomtype = roomtype;
		this.hotel = hotel;
	}

	@ManyToOne(optional=false, fetch=FetchType.LAZY)
	@JoinColumn(name="userId")
	public User getUser() {
		return user;
	}


	public void setUser(User user) {
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



	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
