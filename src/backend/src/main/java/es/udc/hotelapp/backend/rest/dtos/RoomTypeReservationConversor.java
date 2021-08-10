package es.udc.hotelapp.backend.rest.dtos;

import java.util.ArrayList;
import java.util.List;

import es.udc.hotelapp.backend.model.entities.RoomTypeReservation;

public class RoomTypeReservationConversor {

	private RoomTypeReservationConversor() {
	}

	public final static RoomTypeReservation toRoomTypeReservation(RoomTypeReservationDto rtDto) {
		return new RoomTypeReservation(UserConversor.toUser(rtDto.getUser()),rtDto.getInbound(), rtDto.getOutbound(), rtDto.getRooms(),
				RoomTypeConversor.toRoomType(rtDto.getRoomtype()), HotelConversor.toHotel(rtDto.getHotel()));
	}

	public final static RoomTypeReservationDto toRoomTypeReservationDto(RoomTypeReservation rtr) {
		return new RoomTypeReservationDto(rtr.getId(), UserConversor.toUserDto(rtr.getUser()), rtr.getInbound(), rtr.getOutbound(),
				rtr.getRooms(), RoomTypeConversor.toRoomTypeDto(rtr.getRoomtype()),
				HotelConversor.toHotelDto(rtr.getHotel()));
	}
	
	public final static List<RoomTypeReservationDto> toRoomTypeReservationDtos(List<RoomTypeReservation> list){
		List<RoomTypeReservationDto> result = new ArrayList<>();
		
		for(RoomTypeReservation r : list) {
			result.add(toRoomTypeReservationDto(r));
		}
		return result;
	}
}
