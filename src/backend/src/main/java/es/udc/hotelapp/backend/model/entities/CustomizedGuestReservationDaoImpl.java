package es.udc.hotelapp.backend.model.entities;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public class CustomizedGuestReservationDaoImpl implements CustomizedGuestReservationDao {


	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<GuestReservation> find(Long hotelid, String username) {
		
		String queryString = "SELECT gr FROM GuestReservation gr";
		
		if(hotelid != null || !username.isEmpty()) {
			queryString +=  " WHERE ";
		}
		
		if( hotelid != null ) {
			queryString += "gr.reservation.hotel.id = :hotelid";
		}
		
		if( !username.isEmpty()) {
			if(hotelid != null ) {
				queryString += " AND ";
			}
			queryString += "gr.guest.name = :username";
		}
		
		Query query = entityManager.createQuery(queryString);
		
		if(hotelid != null) {
			query.setParameter("hotelid", hotelid);
		}
		
		if(! username.isEmpty()) {
			query.setParameter("username", username.toUpperCase());
		}
		
		
		List<GuestReservation> guests = query.getResultList();
		
		return guests;
	}


}
