package es.udc.hotelapp.backend.model.entities;

import java.util.List;

import org.springframework.data.domain.Slice;

public interface CustomizedRoomTypeReservationDao {
	
	List<RoomTypeReservation> find( Long hotelid, String username, String date);
	
	Slice<RoomTypeReservation> findConflicts( Long hotelid, String type, String date, int page, int size);
	
	Slice<RoomTypeReservation> findBetweenDates( Long hotelid, String type, String in, String out, int page, int size);
	
	List<RoomTypeReservation> find ( Long hotelid, Long userid, String date);

}
