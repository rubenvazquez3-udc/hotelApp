package es.udc.hotelapp.backend.model.entities;

import java.util.List;

public interface CustomizedRoomTypeReservationDao {
	
	List<RoomTypeReservation> find( Long hotelid, String username, String date);

}
