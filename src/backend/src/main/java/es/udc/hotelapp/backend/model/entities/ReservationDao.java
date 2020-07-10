package es.udc.hotelapp.backend.model.entities;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ReservationDao extends PagingAndSortingRepository<Reservation, Long> {
	//Slice <Reservation> findByUserIdReservationByInboundDesc(Long userid, Pageable pageable);

}
