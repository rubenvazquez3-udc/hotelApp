package es.udc.hotelapp.backend.model.entities;

import org.springframework.data.domain.Slice;

public interface CustomizedGuestReservationDao {
	
	Slice<GuestReservation> find ( Long hotelid, String username, int page, int size);

}
