package es.udc.hotelapp.backend.model.entities;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface AccountDao extends PagingAndSortingRepository<Account, Long>{ 
	Optional<Account> findByReservationId( Long reservationid);
}
