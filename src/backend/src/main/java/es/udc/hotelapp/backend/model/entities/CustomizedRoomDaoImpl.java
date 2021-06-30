package es.udc.hotelapp.backend.model.entities;



import javax.persistence.Query;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


public class CustomizedRoomDaoImpl implements CustomizedRoomDao {
	
	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public Slice <Room> find(Long hotelid, String status, String type, int page, int size) {
		
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
		queryString += " ORDER BY r.number";
		
		Query query = entityManager.createQuery(queryString).setFirstResult(page*size).setMaxResults(size+1);
		
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
		boolean hasNext = rooms.size() == (size +1);
		
		if(hasNext) {
			rooms.remove(rooms.size()-1);
		}
		
		return new SliceImpl<>(rooms, PageRequest.of(page, size),hasNext);
	}

}
