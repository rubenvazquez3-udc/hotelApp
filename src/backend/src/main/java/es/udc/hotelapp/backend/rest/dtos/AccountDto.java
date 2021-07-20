package es.udc.hotelapp.backend.rest.dtos;

import java.math.BigDecimal;
import java.util.List;

public class AccountDto {

	private Long id;
	private RoomTypeReservationDto reservation;
	private List<AccountItemDto> items;
	private BigDecimal totalPrice;

	public AccountDto() {
	}

	public AccountDto(Long id, RoomTypeReservationDto reservation, List<AccountItemDto> items, BigDecimal totalPrice) {
		this.id = id;
		this.reservation = reservation;
		this.items = items;
		this.totalPrice = totalPrice;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public RoomTypeReservationDto getReservation() {
		return reservation;
	}

	public void setReservation(RoomTypeReservationDto reservation) {
		this.reservation = reservation;
	}

	public List<AccountItemDto> getItems() {
		return items;
	}

	public void setItems(List<AccountItemDto> items) {
		this.items = items;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

}
