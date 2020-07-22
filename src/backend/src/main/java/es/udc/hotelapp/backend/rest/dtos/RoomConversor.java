package es.udc.hotelapp.backend.rest.dtos;

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
}
