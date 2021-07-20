package es.udc.hotelapp.backend.model.entities;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

public class CustomizedServiceDaoImpl implements CustomizedServiceDao {

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public Slice<Service> find(Long hotelid, int page, int size) {
		String queryString = "SELECT s FROM Service s";
		
		if( hotelid != null) {
			queryString += " WHERE ";
			
			queryString += "s.hotel.id = :hotelid";
		}
		
		queryString += " ORDER BY s.name";
		
		Query query = entityManager.createQuery(queryString).setFirstResult(page*size).setMaxResults(size+1);
		
		query.setParameter("hotelid", hotelid);
		
		List<Service> result = query.getResultList();
		boolean hasNext = result.size() == (size +1);
		
		if(hasNext)
			result.remove(result.size()-1);
		
		return new SliceImpl<>(result, PageRequest.of(page, size), hasNext);
	}

}
