package es.udc.hotelapp.backend.model.entities;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface ServiceDao extends PagingAndSortingRepository<Service, Long>{
	Optional<Service> findByName (String name);

}
