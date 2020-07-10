package es.udc.hotelapp.backend.model.entities;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface RoomReservationDao extends PagingAndSortingRepository<RoomReservation, Long>{

}
