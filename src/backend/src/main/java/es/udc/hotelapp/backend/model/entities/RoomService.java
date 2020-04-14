package es.udc.hotelapp.backend.model.entities;

import java.time.LocalDate;

public class RoomService {
	private Long id;
	private User user;
	private Status status;
	private LocalDate date;
	
	
	public RoomService() {	}
	
	public RoomService(Status status, LocalDate date) {
		
		this.status = status;
		this.date = date;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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
	
	

}
