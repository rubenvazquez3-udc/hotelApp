package es.udc.hotelapp.backend.model.entities;

import java.util.List;

public interface CustomizedGuestReservationDao {
	
	List<GuestReservation> find ( Long hotelid, String username);

}
