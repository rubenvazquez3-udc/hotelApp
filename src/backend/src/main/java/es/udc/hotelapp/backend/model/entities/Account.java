package es.udc.hotelapp.backend.model.entities;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Account {

	private Long id;
	private LocalDateTime inbound;
	private LocalDateTime outbound;
	private Reservation reservation;
	private Set<AccountItem> items = new HashSet<>();

	public Account(Reservation reservation) {
		this.reservation = reservation;
	}

	public Account() {}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	@OneToMany(mappedBy = "account")
	public Set<AccountItem> getItems() {
		return items;
	}

	public void setItems(Set<AccountItem> items) {
		this.items = items;
	}

	public LocalDateTime getInbound() {
		return inbound;
	}

	public void setInbound(LocalDateTime inbound) {
		this.inbound = inbound;
	}

	public LocalDateTime getOutbound() {
		return outbound;
	}

	public void setOutbound(LocalDateTime outbound) {
		this.outbound = outbound;
	}

}
