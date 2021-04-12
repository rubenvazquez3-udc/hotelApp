package es.udc.hotelapp.backend.rest.dtos;

import java.util.List;
import java.util.stream.Collectors;

import es.udc.hotelapp.backend.model.entities.RoomType;

public class RoomTypeConversor {

	private RoomTypeConversor() {}
	
	public final static RoomType toRoomType (RoomTypeDto typeDto) {
		return new RoomType(typeDto.getName());
	}
	
	public final static RoomTypeDto toRoomTypeDto(RoomType type) {
		return new RoomTypeDto(type.getId(),type.getName());
	}
	
	public final static List<RoomTypeDto> toRoomTypeDtos ( List<RoomType> roomtypes){
		return roomtypes.stream().map(c -> toRoomTypeDto(c)).collect(Collectors.toList());
	}
}
