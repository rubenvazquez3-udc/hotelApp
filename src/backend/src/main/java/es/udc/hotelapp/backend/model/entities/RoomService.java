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
public class RoomService {
	private Long id;
	private User user;
	private Status status;
	private LocalDate date;
	private Room room;
	
	
	
	public RoomService() {	}
	
	public RoomService(Status status, LocalDate date) {
		
		this.status = status;
		this.date = date;
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
	@JoinColumn(name="userId")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	@ManyToOne(optional=false, fetch= FetchType.LAZY)
	@JoinColumn(name="roomId")
	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}
	
	

}
