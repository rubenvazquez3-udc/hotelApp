package es.udc.hotelapp.backend.model.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.hotelapp.backend.model.entities.Hotel;
import es.udc.hotelapp.backend.model.entities.Reservation;
import es.udc.hotelapp.backend.model.entities.ReservationDao;
import es.udc.hotelapp.backend.model.entities.Room;
import es.udc.hotelapp.backend.model.entities.RoomDao;
import es.udc.hotelapp.backend.model.entities.RoomReservation;
import es.udc.hotelapp.backend.model.entities.RoomReservationDao;
import es.udc.hotelapp.backend.model.entities.RoomType;
import es.udc.hotelapp.backend.model.entities.RoomTypeDao;
import es.udc.hotelapp.backend.model.entities.RoomTypeReservation;
import es.udc.hotelapp.backend.model.entities.RoomTypeReservationDao;
import es.udc.hotelapp.backend.model.entities.Status;
import es.udc.hotelapp.backend.model.exceptions.InstanceNotFoundException;

@Service
@Transactional
public class AdministrationServiceImpl implements administrationService{
	
	@Autowired
	private RoomDao roomDao;
	@Autowired
	private ReservationDao reservationDao;
	@Autowired
	private RoomTypeReservationDao roomTypeReservationDao;
	@Autowired
	private RoomReservationDao roomReservationDao;
	@Autowired
	private RoomTypeDao roomTypeDao;
	
	

	@Override
	public Long addRoom(Long number, RoomType type, Hotel hotel) {
		Long id = null;
		Iterable<Room> room = roomDao.findAll();
				
		if(room != null) {
			Iterator<Room> iter = room.iterator();
			while (iter.hasNext()){
				Room roomfound = iter.next();
				if (roomfound.getNumber() == number && roomfound.getType() == type && roomfound.getHotel() == hotel) {
					id =  roomfound.getId();
					//throw new RoomAlreadyExistsException();
				}	
			}
		} else {
			Room r1 = new Room (number,type,hotel);
			roomDao.save(r1);
			id = r1.getId();
		}
		return id;
		
	}

	@Override
	public void updateRoom(Room r1) {
		Optional<Room> roomFound = roomDao.findById(r1.getId());
		if( roomFound.isPresent()) {
			
			Room roomActual = roomFound.get();
			roomActual.setNumber(r1.getNumber());
			switch (roomActual.getStatus()) {
			
			case LIBRE :
				if ( r1.getStatus() == Status.RESERVADA)
					roomActual.setStatus(Status.RESERVADA);
				//else throw new ......
				break;
			case RESERVADA:
				if ( r1.getStatus() == Status.OCUPADA)
					roomActual.setStatus(Status.OCUPADA);
				break;
			case OCUPADA:
				if ( r1.getStatus() == Status.SIN_LIMPIAR)
					roomActual.setStatus(Status.SIN_LIMPIAR);
				break;
			
			default:
				break;
				/*
				throw new UpdateStatusIncorrectEXception
			
			*/
			}
			
			
			roomActual.setType(r1.getType());
			roomActual.setHotel(r1.getHotel());
			
			roomDao.save(roomActual);
		}
		
	}

	@Override
	public void removeRoom(Room r1) throws InstanceNotFoundException {
		if (roomDao.findById(r1.getId()) != null) {
			roomDao.delete(r1);
		} else throw new InstanceNotFoundException("project.entities.room",r1.getId());
				
	}

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
	public List<Room> findUsedRooms() {
		List<Room> rooms = new ArrayList<>();
		List<Room> roomsfound = (List<Room>) roomDao.findAll();
			if (!roomsfound.isEmpty()) {
				Iterator<Room> iter = roomsfound.iterator();
				while (iter.hasNext()) {
					Room room = iter.next();
					if (room.getStatus() != Status.LIBRE )
						rooms.add(room);
					}
			}
		return rooms;
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
	public List<Room> findAvailableRooms() {
		List<Room> rooms = new ArrayList<>();
		List<Room> roomsfound = (List<Room>) roomDao.findAll();
			if (!roomsfound.isEmpty()) {
				Iterator<Room> iter = roomsfound.iterator();
				while (iter.hasNext()) {
					Room room = iter.next();
					if (room.getStatus() == Status.LIBRE )
						rooms.add(room);
					}
			}
		return rooms;
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
