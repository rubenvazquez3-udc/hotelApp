package es.udc.hotelapp.backend.model.entities;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface RoomDao extends PagingAndSortingRepository<Room, Long> {
	boolean existsByNumber(int name);
	Optional<Room> findByNumber(int number);

}
