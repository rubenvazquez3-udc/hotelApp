package es.udc.hotelapp.backend.rest.dtos;

import es.udc.hotelapp.backend.model.entities.RoomTypeReservation;

public class RoomTypeReservationConversor {

	private RoomTypeReservationConversor() {
	}

	public final static RoomTypeReservation toRoomTypeReservation(RoomTypeReservationDto rtDto) {
		return new RoomTypeReservation(ReservationConversor.toReservation(rtDto.getReservation()), rtDto.getRooms(),
				RoomTypeConversor.toRoomType(rtDto.getRoomtype()), HotelConversor.toHotel(rtDto.getHotel()));
	}

	public final static RoomTypeReservationDto toRoomTypeReservationDto(RoomTypeReservation rtr) {
		return new RoomTypeReservationDto(rtr.getId(), ReservationConversor.toReservationDto(rtr.getReservation()),
				rtr.getRooms(), RoomTypeConversor.toRoomTypeDto(rtr.getRoomtype()),
				HotelConversor.toHotelDto(rtr.getHotel()));
	}
}
