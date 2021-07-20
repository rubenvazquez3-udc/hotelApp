package es.udc.hotelapp.backend.model.entities;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductDao extends PagingAndSortingRepository<Product, Long>, CustomizedProductDao{
	
	boolean existsByName(String name);
	
	Optional<Product> findByName(String name);


}
