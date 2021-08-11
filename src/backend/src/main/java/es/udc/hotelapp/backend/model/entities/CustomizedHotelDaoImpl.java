package es.udc.hotelapp.backend.model.entities;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

public class CustomizedHotelDaoImpl implements CustomizedHotelDao {

    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    @Override
    public Slice<Hotel> find(String name, String address, int page, int size) {
        String queryString = "SELECT h FROM Hotel h";

        if (!name.isEmpty() || !address.isEmpty()) {
            queryString += " WHERE ";
        }

        if (!name.isEmpty()) {
            queryString += "h.name LIKE :name";
        }

        if (!address.isEmpty()) {
            if (!name.isEmpty()) {
                queryString += " AND ";
            }
            queryString += "h.address LIKE :address";
        }

        queryString += " ORDER BY h.name";

        Query query = entityManager.createQuery(queryString).setFirstResult(page * size).setMaxResults(size + 1);


        if (!name.isEmpty())
            query.setParameter("name", "%" + name + "%");

        if (!address.isEmpty())
            query.setParameter("address", "%" + address + "%");

        List<Hotel> result = query.getResultList();
        boolean hasNext = result.size() == (size + 1);

        if (hasNext)
            result.remove(result.size() - 1);

        return new SliceImpl<>(result, PageRequest.of(page, size), hasNext);
    }

}
