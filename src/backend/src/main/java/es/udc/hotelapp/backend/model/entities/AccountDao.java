package es.udc.hotelapp.backend.model.entities;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface AccountDao extends PagingAndSortingRepository<Account, Long>, CustomizedAccountDao{ 
	Optional<Account> findByReservationId( Long reservationid);
}
