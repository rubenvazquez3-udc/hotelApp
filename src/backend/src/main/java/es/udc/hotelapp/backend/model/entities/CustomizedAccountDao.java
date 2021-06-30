package es.udc.hotelapp.backend.model.entities;

import java.util.List;

public interface CustomizedAccountDao {
	
	public List<Account> find( Long reservationid, Long userid, String date);

}
