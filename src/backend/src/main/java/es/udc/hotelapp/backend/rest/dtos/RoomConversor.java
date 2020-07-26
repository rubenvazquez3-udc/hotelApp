package es.udc.hotelapp.backend.rest.dtos;

import java.util.ArrayList;
import java.util.List;

import es.udc.hotelapp.backend.model.entities.Room;

public class RoomConversor {

	private RoomConversor() {
	}

	public final static Room toRoom(RoomDto roomDto) {
		return new Room(roomDto.getNumber(), StatusConversor.toStatus(roomDto.getStatus()),
				RoomTypeConversor.toRoomType(roomDto.getType()), HotelConversor.toHotel(roomDto.getHotel()));
	}

	public final static RoomDto toRoomDto(Room room) {
		return new RoomDto(room.getId(), room.getNumber(), StatusConversor.toStatusDto(room.getStatus()),
				RoomTypeConversor.toRoomTypeDto(room.getType()), HotelConversor.toHotelDto(room.getHotel()));
	}
	
	public final static List<RoomDto> toRoomDtos(List<Room> list){
		List<RoomDto>  result = new ArrayList<>();
		for (Room r : list){
			result.add(toRoomDto(r));
		}
		return result;
	}
}
