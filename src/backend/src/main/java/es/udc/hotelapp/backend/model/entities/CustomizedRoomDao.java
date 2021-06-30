package es.udc.hotelapp.backend.model.entities;

import org.springframework.data.domain.Slice;

public interface CustomizedRoomDao {
	
	public Slice<Room> find ( Long hotelid, String status, String type, int page, int size);

}
