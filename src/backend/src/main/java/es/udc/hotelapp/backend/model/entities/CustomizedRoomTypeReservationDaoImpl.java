package es.udc.hotelapp.backend.model.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

@SuppressWarnings("unchecked")
public class CustomizedRoomTypeReservationDaoImpl
		implements CustomizedRoomTypeReservationDao{

	@PersistenceContext
	private EntityManager entityManager;

	
	@Override
	public Slice<RoomTypeReservation> find(Long hotelid, String username, String date, int page, int size) {
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
		Query query = entityManager.createQuery(queryString).setFirstResult(page*size).setMaxResults(size+1);
		
		if(hotelid != null) {
			query.setParameter("hotelid", hotelid);
		}
		
		if(! username.isEmpty()) {
			query.setParameter("username", "%"+username+"%");
		}
		
		if(! date.isEmpty()) {
			query.setParameter("date", LocalDate.parse(date));
		}
		
		List<RoomTypeReservation> reservations = query.getResultList();
		boolean hasNext = reservations.size() == (size+1);
		
		if(hasNext) {
			reservations.remove(reservations.size()-1);
		}
		
		return new SliceImpl<>(reservations, PageRequest.of(page, size),hasNext);

	}

	@Override
	public Slice<RoomTypeReservation> findConflicts(Long hotelid, String type, String date, int page, int size) {
		String queryString = "SELECT rt FROM RoomTypeReservation rt WHERE ";
		
		if( hotelid != null ) {
		queryString += "rt.hotel.id = :hotelid";
		}
		
		queryString += " AND ";
		
		if( !type.isEmpty() ) {
			queryString += "rt.roomtype.name = :type";
		}
		
		queryString += " AND ";
		
		if( ! date.isEmpty()) {
		queryString += "rt.inbound < :date1 ";
		}
		if( ! date.isEmpty() ) {
		queryString += "AND rt.outbound > :date2";
		}
		
		Query query = entityManager.createQuery(queryString).setFirstResult(page*size).setMaxResults(size+1);
		
		query.setParameter("hotelid", hotelid);
		query.setParameter("type", type);
		query.setParameter("date1", LocalDate.parse(date));
		query.setParameter("date2", LocalDate.parse(date));
		
		
		List<RoomTypeReservation> reservations = query.getResultList();
		
		boolean hasNext = reservations.size() == size+1;
		
		if(hasNext)
			reservations.remove(reservations.size()-1);
		
		return new SliceImpl<>(reservations, PageRequest.of(page, size),hasNext);
	}

	@Override
	public Slice<RoomTypeReservation> findBetweenDates(Long hotelid, String type, String in, String out, int page,
			int size) {
		String queryString = "SELECT rt FROM RoomTypeReservation rt WHERE ";
		
		queryString += "rt.hotel.id = :hotelid";
		
		queryString += " AND ";
		
		queryString += "rt.roomtype.name = :type";
		
		queryString += " AND ";
		
		queryString += "rt.inbound BETWEEN :date1 AND :date2";
		
		Query query = entityManager.createQuery(queryString).setFirstResult(page*size).setMaxResults(size+1);
		
		query.setParameter("hotelid", hotelid);
		query.setParameter("type", type);
		query.setParameter("date1", LocalDate.parse(in));
		query.setParameter("date2", LocalDate.parse(out).minusDays(1));
		
		
		List<RoomTypeReservation> reservations = query.getResultList();
		
		boolean hasNext = reservations.size() == size+1;
		
		if(hasNext)
			reservations.remove(reservations.size()-1);
		
		return new SliceImpl<>(reservations, PageRequest.of(page, size),hasNext);
	}

	
	@Override
	public List<RoomTypeReservation> find(Long hotelid, Long userid, String date) {
		String queryString = "SELECT rt FROM RoomTypeReservation rt";
		
		if(hotelid != null || userid != null || !date.isEmpty()) {
			queryString +=  " WHERE ";
		}
		
		if( hotelid != null ) {
			queryString += "rt.hotel.id = :hotelid";
		}
		
		if( userid != null) {
			if(hotelid != null ) {
				queryString += " AND ";
			}
			queryString += "rt.user.id = :userid";
		}
		
		if(! date.isEmpty()) {
			if(hotelid != null && userid != null) {
				queryString += " AND ";
			}
			if( userid == null && hotelid != null) {
				queryString += " AND ";
			} 
			if(userid != null && hotelid == null)
				queryString += " AND ";
			
			queryString += " rt.inbound <= :date";
			
		}
		
		if( ! date.isEmpty() ) {
			queryString += " AND rt.outbound > :date2";
			}
		
		Query query = entityManager.createQuery(queryString);
		
		query.setParameter("hotelid", hotelid);
		query.setParameter("userid", userid);
		query.setParameter("date", LocalDate.parse(date));
		query.setParameter("date2", LocalDate.parse(date));
		
		
		List<RoomTypeReservation> reservations = query.getResultList();
		
		return reservations;
	}

}
