package es.udc.hotelapp.backend.rest.dtos;

import es.udc.hotelapp.backend.model.entities.RoomReservation;

public class RoomReservationConversor {

	private RoomReservationConversor() {
	}

	public final static RoomReservation toRoomReservation(RoomReservationDto rrDto) {
		return new RoomReservation(RoomConversor.toRoom(rrDto.getRoom()),
				ReservationConversor.toReservation(rrDto.getReservation()), rrDto.getBegin(), rrDto.getEnd());
	}

	public final static RoomReservationDto toRoomReservationDto(RoomReservation rr) {
		return new RoomReservationDto(rr.getId(), RoomConversor.toRoomDto(rr.getRoom()),
				ReservationConversor.toReservationDto(rr.getReservation()), rr.getBegin(), rr.getEnd());
	}

}
