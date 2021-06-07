package es.udc.hotelapp.backend.model.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public class CustomizedRoomTypeReservationDaoImpl
		implements CustomizedRoomTypeReservationDao{

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<RoomTypeReservation> find(Long hotelid, String username, String date) {
String queryString = "SELECT rt FROM RoomTypeReservation rt";
		
		if(hotelid != null || !username.isEmpty() || !date.isEmpty()) {
			queryString +=  " WHERE ";
		}
		
		if( hotelid != null ) {
			queryString += "rt.hotel.id = :hotelid";
		}
		
		if( !username.isEmpty()) {
			if(hotelid != null ) {
				queryString += " AND ";
			}
			queryString += "rt.user.firstName LIKE :username";
		}
		
		if(! date.isEmpty()) {
			if(hotelid != null && !username.isEmpty()) {
				queryString += " AND ";
			} else if( username.isEmpty()) {
				if(hotelid != null) {
					queryString += " AND ";
				}
			}else queryString += " AND ";
			queryString += "rt.inbound = :date";
		}
		Query query = entityManager.createQuery(queryString);
		
		if(hotelid != null) {
			query.setParameter("hotelid", hotelid);
		}
		
		if(! username.isEmpty()) {
			query.setParameter("username", username);
		}
		
		if(! date.isEmpty()) {
			query.setParameter("date", LocalDate.parse(date));
		}
		
		List<RoomTypeReservation> reservations = query.getResultList();
		
		return reservations;

	}

}
