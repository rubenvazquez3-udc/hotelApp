package es.udc.hotelapp.backend.model.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.hotelapp.backend.model.entities.Reservation;
import es.udc.hotelapp.backend.model.entities.ReservationDao;
import es.udc.hotelapp.backend.model.entities.Room;
import es.udc.hotelapp.backend.model.entities.RoomReservation;
import es.udc.hotelapp.backend.model.entities.RoomReservationDao;
import es.udc.hotelapp.backend.model.entities.RoomType;
import es.udc.hotelapp.backend.model.entities.RoomTypeDao;
import es.udc.hotelapp.backend.model.entities.RoomTypeReservation;
import es.udc.hotelapp.backend.model.entities.RoomTypeReservationDao;

@Service
@Transactional
public class AdministrationServiceImpl implements administrationService{
	
	
	@Autowired
	private ReservationDao reservationDao;
	@Autowired
	private RoomTypeReservationDao roomTypeReservationDao;
	@Autowired
	private RoomReservationDao roomReservationDao;
	@Autowired
	private RoomTypeDao roomTypeDao;
	


	@Override
	public Room findRoom(Long userId, Long reservationId) {
		Optional<Reservation> reservationfound = reservationDao.findById(reservationId);
		Room result = null;
		if (reservationfound.get().getUser().getId() != userId) {
			//throw new exception
		} else {
			Iterable<RoomReservation> roomreservation = roomReservationDao.findAll();
			if(roomreservation != null) {
				Iterator<RoomReservation> iter = roomreservation.iterator();
				while (iter.hasNext()) {
					RoomReservation next = iter.next();
					if ( next.getReservation().getId() == reservationId && 
						next.getReservation().getUser().getId() == userId) {
							result = next.getRoom();
					}
				}
			}
		}
		return result;
	}

	

	@Override
	public List<Reservation> findReservations(LocalDate inbound) {
		List<Reservation> reservations = new ArrayList<Reservation>();
		List<Reservation> reservationsfound = (List<Reservation>) reservationDao.findAll();
			if (!reservationsfound.isEmpty()) {
				Iterator<Reservation> iter = reservationsfound.iterator();
				while ( iter.hasNext()) {
					Reservation next = iter.next();
					if ( next.getInbound() == inbound)
						reservations.add(next);
				}
			}
		return reservations;
	}




	@Override
	public Reservation findReservation(RoomTypeReservation rtr1) {
		return roomTypeReservationDao.findById(rtr1.getId()).get().getReservation();
	}

	@Override
	public void updateReservation(Reservation r1) {
		Optional<Reservation> reservationActual = reservationDao.findById(r1.getId());
		if(reservationActual.isPresent()) {
			Reservation reservation = reservationActual.get();
			
			reservation.setInbound(r1.getInbound());
			reservation.setOutbound(r1.getOutbound());
			
		} // else throw new exception 
		
		
	}

	@Override
	public Long addReservation(Reservation r1, RoomType rt1, int quantity) {
		
		RoomTypeReservation rtr1 = new RoomTypeReservation (r1,quantity,rt1);
		roomTypeReservationDao.save(rtr1);
		return rtr1.getId();
	}

	@Override
	public Long addRoomReservation(Reservation r1, Room r2) {
		
		RoomReservation rr1 = new RoomReservation(r2,r1,r1.getInbound(),r1.getOutbound());
		roomReservationDao.save(rr1);
		return rr1.getId();
	}

	@Override
	public Long createReservation(Reservation reservation) {
		//Optional<User> userfound = userDao.reservation.getUser().getId());
		Long id = null;
		Iterable<Reservation> reservations = reservationDao.findAll();
		if ( reservations != null) {
			Iterator<Reservation> iter = reservations.iterator();
			while (iter.hasNext()) {
				Reservation r1 = iter.next();
				if (r1.equals(reservation)) {
					id = r1.getId();
					//trow new exception
				}
			}
		} else {
			reservationDao.save(reservation);
			id = reservation.getId();
		}
		return id;
	}

	@Override
	public Long createRoomType(String name) {
		Long id = null;
		Iterable<RoomType> roomtype = roomTypeDao.findAll();
		if (roomtype != null) {
			Iterator<RoomType> iter = roomtype.iterator();
			while(iter.hasNext()) {
				RoomType rt1 = iter.next();
				if(rt1.getName() == name ) {
					//throw new exception
				}
			}
		} else {
			RoomType roomtype1 = new RoomType(name);
			roomTypeDao.save(roomtype1);
			id = roomtype1.getId();
		}
		return id;
	}

	

}
