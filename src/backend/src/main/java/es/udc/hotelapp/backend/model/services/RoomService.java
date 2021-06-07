package es.udc.hotelapp.backend.model.services;

import java.util.List;

import es.udc.hotelapp.backend.model.entities.Room;
import es.udc.hotelapp.backend.model.entities.RoomType;
import es.udc.hotelapp.backend.model.exceptions.InstanceNotFoundException;
import es.udc.hotelapp.backend.model.exceptions.RoomAlreadyExistsException;

public interface RoomService {
	
	public Long addRoom(Room room) throws RoomAlreadyExistsException, InstanceNotFoundException;

	public void updateRoom(Room r1);

	public void removeRoom(Room r1) throws InstanceNotFoundException;

	public Room findRoom(Long id) throws InstanceNotFoundException;

	public List<Room> findRooms(String status, Long hotelid, String type);
	
	public List<RoomType> findAllRoomTypes();
}
