package es.udc.hotelapp.backend.model.entities;

import java.time.LocalDate;

public class Reservation {
	private Long id;
	private Long idUser;
	private Long idRoom;
	private LocalDate inbound;
	private LocalDate outbound;
	
	public Reservation() {

	}

	public Reservation(Long id, Long idUser, Long idRoom, LocalDate inbound, LocalDate outbound) {
		super();
		this.id = id;
		this.idUser = idUser;
		this.idRoom = idRoom;
		this.inbound = inbound;
		this.outbound = outbound;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public Long getIdRoom() {
		return idRoom;
	}

	public void setIdRoom(Long idRoom) {
		this.idRoom = idRoom;
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


}
