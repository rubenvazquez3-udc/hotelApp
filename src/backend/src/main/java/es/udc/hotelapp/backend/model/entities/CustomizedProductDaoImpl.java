package es.udc.hotelapp.backend.model.entities;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

public class CustomizedProductDaoImpl implements CustomizedProductDao {
	
	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public Slice<Product> find(Long hotelid,String name, int page, int size) {
		String queryString = "SELECT p FROM Product p";
		
		if( hotelid != null || !name.isEmpty()) {
			queryString += " WHERE ";
		}

		if(hotelid != null){
			queryString += "p.hotel.id = :hotelid";
		}

		if(! name.isEmpty()){
			if(hotelid != null){
				queryString += " AND ";
			}
			queryString += "p.name LIKE :name";
		}
		
		queryString += " ORDER BY p.name";
		
		Query query = entityManager.createQuery(queryString).setFirstResult(page*size).setMaxResults(size+1);

		if(hotelid != null)
			query.setParameter("hotelid", hotelid);

		if(! name.isEmpty())
			query.setParameter("name",  "%"+name+"%");
		
		List<Product> result = query.getResultList();
		boolean hasNext = result.size() == (size +1);
		
		if(hasNext)
			result.remove(result.size()-1);
		
		return new SliceImpl<>(result, PageRequest.of(page, size), hasNext);
	}

}
