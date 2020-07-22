package es.udc.hotelapp.backend.model.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Room {
	
	private Long id;
	private int number;
	private Status status;
	private RoomType type;
	private Hotel hotel;

	public Room() { }

	public Room(int number, RoomType type, Hotel hotel) {
		super();
		this.number = number;
		this.status = Status.LIBRE;
		this.type = type;
		this.hotel = hotel;
	}
	

	public Room(int number, Status status, RoomType type, Hotel hotel) {
		this.number = number;
		this.status = status;
		this.type = type;
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

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@ManyToOne(optional=false, fetch=FetchType.LAZY)
	@JoinColumn(name="typeId")
	public RoomType getType() {
		return type;
	}

	public void setType(RoomType type) {
		this.type = type;
	}

	@ManyToOne(optional=false, fetch=FetchType.LAZY)
	@JoinColumn(name="hotelId")
	public Hotel getHotel() {
		return hotel;
	}



	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}


}
