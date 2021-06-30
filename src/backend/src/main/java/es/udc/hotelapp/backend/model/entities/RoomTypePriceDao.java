package es.udc.hotelapp.backend.model.entities;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface RoomTypePriceDao extends PagingAndSortingRepository<RoomTypePrice, Long>{
	
	Optional<RoomTypePrice> findByTypeIdAndHotelId(Long typeid, Long hotelid);

}
