package es.udc.hotelapp.backend.model.entities;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface HotelDao extends PagingAndSortingRepository <Hotel, Long>, CustomizedHotelDao{
	boolean existsByName(String name);

}
