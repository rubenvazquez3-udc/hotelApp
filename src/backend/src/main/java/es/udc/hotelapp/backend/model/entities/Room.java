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
	private Long number;
	private Status status;
	private RoomType type;
	private Hotel hotel;

	public Room() { }

	public Room(Long number, RoomType type, Hotel hotel) {
		super();
		this.number = number;
		this.status = Status.LIBRE;
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

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@ManyToOne(optional=false, fetch=FetchType.LAZY)
	@JoinColumn(name="roomType")
	public RoomType getType() {
		return type;
	}

	public void setType(RoomType type) {
		this.type = type;
	}


	public Hotel getHotel() {
		return hotel;
	}



	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}


}
