package es.udc.hotelapp.backend.model.entities;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface RoomTypeDao extends PagingAndSortingRepository<RoomType, Long>{

	boolean existsByName(String name);
	Optional<RoomType> findByName(String name);

}
