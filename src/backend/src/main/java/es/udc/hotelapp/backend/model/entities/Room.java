package es.udc.hotelapp.backend.model.entities;

public class Room {
	private Long id;
	private Long number;
	private Status status;
	private tRoom type;
	private Reservation reservation;

	public Room() {

	}

	public Room(Long id, Long number, Status status, tRoom type, Reservation reservation) {
		super();
		this.id = id;
		this.number = number;
		this.status = status;
		this.type = type;
		this.reservation = reservation;
	}

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

	public tRoom getType() {
		return type;
	}

	public void setType(tRoom type) {
		this.type = type;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

}
