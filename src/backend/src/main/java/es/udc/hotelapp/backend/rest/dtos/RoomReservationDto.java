package es.udc.hotelapp.backend.rest.dtos;

import java.time.LocalDate;

public class RoomReservationDto {
	private Long id;
	private RoomDto room;
	private ReservationDto reservation;
	private LocalDate begin;
	private LocalDate end;

	public RoomReservationDto(Long id, RoomDto room, ReservationDto reservation, LocalDate begin, LocalDate end) {
		this.id = id;
		this.room = room;
		this.reservation = reservation;
		this.begin = begin;
		this.end = end;
	}

	public RoomReservationDto() {	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public RoomDto getRoom() {
		return room;
	}

	public void setRoom(RoomDto room) {
		this.room = room;
	}

	public ReservationDto getReservation() {
		return reservation;
	}

	public void setReservation(ReservationDto reservation) {
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
