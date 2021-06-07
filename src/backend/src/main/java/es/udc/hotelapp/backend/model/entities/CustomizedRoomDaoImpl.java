package es.udc.hotelapp.backend.model.entities;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


public class CustomizedRoomDaoImpl implements CustomizedRoomDao {
	
	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<Room> find(Long hotelid, String status, String type) {
		
		String queryString = "SELECT r FROM Room r";
		
		if(hotelid != null || !status.isEmpty() || !type.isEmpty()) {
			queryString +=  " WHERE ";
		}
		
		if( hotelid != null ) {
			queryString += "r.hotel.id = :hotelid";
		}
		
		if( !status.isEmpty()) {
			if(hotelid != null ) {
				queryString += " AND ";
			}
			queryString += "r.status = :status";
		}
		
		if(! type.isEmpty()) {
			if(hotelid != null && !status.isEmpty()) {
				queryString += " AND ";
			} else if( status.isEmpty()) {
				if(hotelid != null) {
					queryString += " AND ";
				}
			}else queryString += " AND ";
			queryString += "r.type.name = :type";
		}
		Query query = entityManager.createQuery(queryString);
		
		if(hotelid != null) {
			query.setParameter("hotelid", hotelid);
		}
		
		if(! status.isEmpty()) {
			query.setParameter("status", Status.valueOf(status));
		}
		
		if(! type.isEmpty()) {
			query.setParameter("type", type);
		}
		
		List<Room> rooms = query.getResultList();
		
		return rooms;
	}

}
