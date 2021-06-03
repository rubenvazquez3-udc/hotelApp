package es.udc.hotelapp.backend.rest.dtos;

import es.udc.hotelapp.backend.model.entities.Guest;

public class GuestConversor {
	
	private GuestConversor() {}
	
	public final static Guest toGuest(GuestDto guestDto) {
		return new Guest(guestDto.getName(), guestDto.getSurname(), guestDto.getDni(), guestDto.getAddress(),guestDto.getPhoneNumber());
	}

	public final static GuestDto toGuestDto(Guest guest) {
		return new GuestDto(guest.getId(),guest.getName(),guest.getSurname(),guest.getDni(), guest.getAddress(), guest.getPhoneNumber());
	}
}
