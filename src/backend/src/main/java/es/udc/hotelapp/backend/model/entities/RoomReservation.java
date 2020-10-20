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
public class RoomReservation {
	
	private Long id;
	private Room room;
	private RoomTypeReservation reservation;
	private LocalDate begin;
	private LocalDate end;
	
	
	
	public RoomReservation() {	}
	
	public RoomReservation(Room room, RoomTypeReservation reservation, LocalDate begin, LocalDate end) {
		super();
		this.room = room;
		this.reservation = reservation;
		this.begin = begin;
		this.end = end;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@ManyToOne(optional=false, fetch=FetchType.LAZY)
	@JoinColumn(name="roomId")
	public Room getRoom() {
		return room;
	}
	public void setRoom(Room room) {
		this.room = room;
	}
	
	@ManyToOne(optional=false, fetch=FetchType.LAZY)
	@JoinColumn(name="reservationId")
	public RoomTypeReservation getReservation() {
		return reservation;
	}
	public void setReservation(RoomTypeReservation reservation) {
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
