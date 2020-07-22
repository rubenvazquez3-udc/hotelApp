package es.udc.hotelapp.backend.rest.dtos;

import java.time.LocalDate;


public class ReservationDto {
	
	private Long id;
	private UserDto user;
	private LocalDate inbound;
	private LocalDate outbound;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public UserDto getUser() {
		return user;
	}
	public void setUser(UserDto user) {
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
	public ReservationDto(Long id, UserDto user, LocalDate inbound, LocalDate outbound) {
		super();
		this.id = id;
		this.user = user;
		this.inbound = inbound;
		this.outbound = outbound;
	}
	public ReservationDto() {	}
	
	
	
	
	

}
