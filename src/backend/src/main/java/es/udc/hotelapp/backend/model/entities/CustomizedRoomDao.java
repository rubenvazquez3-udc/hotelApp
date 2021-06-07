package es.udc.hotelapp.backend.model.entities;

import java.util.List;

public interface CustomizedRoomDao {
	
	public List<Room> find ( Long hotelid, String status, String type);

}
