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
public class Reservation {
	
	private Long id;
	private User user;
	private LocalDate inbound;
	private LocalDate outbound;
	
	public Reservation() {	}

	public Reservation(User user, LocalDate inbound, LocalDate outbound) {
		this.user = user;
		this.inbound = inbound;
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

	


}
