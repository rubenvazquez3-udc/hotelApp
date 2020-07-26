package es.udc.hotelapp.backend.rest.dtos;

import es.udc.hotelapp.backend.model.entities.GuestReservation;

public class GuestReservationConversor {

	private GuestReservationConversor() {
	}

	public final static GuestReservation toGuestReservation(GuestReservationDto grDto) {
		return new GuestReservation(ReservationConversor.toReservation(grDto.getReservation()),
				GuestConversor.toGuest(grDto.getGuest()));
	}

	public final static GuestReservationDto toGuestReservationDto(GuestReservation gr) {
		return new GuestReservationDto(gr.getId(), ReservationConversor.toReservationDto(gr.getReservation()),
				GuestConversor.toGuestDto(gr.getGuest()));
	}

}
