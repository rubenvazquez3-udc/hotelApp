package es.udc.hotelapp.backend.model.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public class CustomizedAccountDaoImpl implements CustomizedAccountDao {
	
	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<Account> find(Long reservationid, Long userid, String date) {
		String queryString = "SELECT a FROM Account a";
		
		if(reservationid != null || userid != null || !date.isEmpty()) {
			queryString +=  " WHERE ";
		}
		
		if( reservationid != null ) {
			queryString += "a.reservation.id = :reservationid";
		}
		
		if( userid != null) {
			if(reservationid != null ) {
				queryString += " AND ";
			}
			queryString += "a.reservation.user.id = :userid";
		}
		
		if(! date.isEmpty()) {
			if(reservationid != null && userid != null) {
				queryString += " AND ";
			} else if( userid == null) {
				if(reservationid != null) {
					queryString += " AND ";
				}
			}else queryString += " AND ";
			queryString += "a.inbound = :date";
		}
		Query query = entityManager.createQuery(queryString);
		
		if(reservationid != null) {
			query.setParameter("reservationid", reservationid);
		}
		
		if( userid != null) {
			query.setParameter("userid", userid);
		}
		
		if(! date.isEmpty()) {
			query.setParameter("date", LocalDate.parse(date));
		}
		
		List<Account> account = query.getResultList();
		
		return account;

	
	}

}
