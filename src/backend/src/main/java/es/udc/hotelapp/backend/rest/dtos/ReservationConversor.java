package es.udc.hotelapp.backend.rest.dtos;

import es.udc.hotelapp.backend.model.entities.Reservation;

public class ReservationConversor {

	private ReservationConversor() {
	}

	public final static ReservationDto toReservationDto(Reservation reservation) {
		return new ReservationDto(reservation.getId(), UserConversor.toUserDto(reservation.getUser()),
				reservation.getInbound(), reservation.getOutbound());
	}

	public final static Reservation toReservation(ReservationDto reservationDto) {
		return new Reservation(UserConversor.toUser(reservationDto.getUser()), reservationDto.getInbound(),
				reservationDto.getOutbound());
	}
}
