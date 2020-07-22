package es.udc.hotelapp.backend.model.entities;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface GuestDao extends PagingAndSortingRepository<Guest, Long>{
	
	Optional<Guest> findByDni(String name);
	
	boolean existsByDni(String dni);

}
