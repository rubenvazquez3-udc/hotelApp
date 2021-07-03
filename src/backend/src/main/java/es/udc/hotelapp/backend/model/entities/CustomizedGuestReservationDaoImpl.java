package es.udc.hotelapp.backend.model.entities;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

public class CustomizedGuestReservationDaoImpl implements CustomizedGuestReservationDao {


	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public Slice<GuestReservation> find(Long hotelid, String username, int page, int size) {
		
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
		
		queryString += " ORDER BY gr.guest.name";
		
		Query query = entityManager.createQuery(queryString).setFirstResult(page*size).setMaxResults(size+1);
		
		if(hotelid != null) {
			query.setParameter("hotelid", hotelid);
		}
		
		if(! username.isEmpty()) {
			query.setParameter("username", username.toUpperCase());
		}
		
		
		List<GuestReservation> guests = query.getResultList();
		boolean hasNext = guests.size() == (size+1);
		
		if(hasNext) {
			guests.remove(guests.size()-1);
		}
		
		return new SliceImpl<>(guests, PageRequest.of(page, size),hasNext);
	}


}
