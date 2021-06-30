package es.udc.hotelapp.backend.model.entities;

import java.util.Optional;

import org.springframework.data.domain.Slice;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductDao extends PagingAndSortingRepository<Product, Long>{
	
	boolean existsByName(String name);
	
	Optional<Product> findByName(String name);
	
	Slice<Product> findByHotelId(Long id);

}
