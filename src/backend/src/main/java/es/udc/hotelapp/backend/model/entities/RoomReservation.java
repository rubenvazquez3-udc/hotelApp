package es.udc.hotelapp.backend.model.entities;

import java.time.LocalDate;

public class RoomReservation {
	private Long id;
	private Room room;
	private Reservation reservation;
	private LocalDate begin;
	private LocalDate end;
	
	
	
	public RoomReservation() {	}
	
	public RoomReservation(Room room, Reservation reservation, LocalDate begin, LocalDate end) {
		super();
		this.room = room;
		this.reservation = reservation;
		this.begin = begin;
		this.end = end;
	}


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Room getRoom() {
		return room;
	}
	public void setRoom(Room room) {
		this.room = room;
	}
	public Reservation getReservation() {
		return reservation;
	}
	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}
	public LocalDate getBegin() {
		return begin;
	}
	public void setBegin(LocalDate begin) {
		this.begin = begin;
	}
	public LocalDate getEnd() {
		return end;
	}
	public void setEnd(LocalDate end) {
		this.end = end;
	}
	
	

}
