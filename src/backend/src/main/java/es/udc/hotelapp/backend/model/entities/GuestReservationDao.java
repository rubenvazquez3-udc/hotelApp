package es.udc.hotelapp.backend.model.entities;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface GuestReservationDao extends PagingAndSortingRepository<GuestReservation, Long>, CustomizedGuestReservationDao{

}
