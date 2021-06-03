package es.udc.hotelapp.backend.model.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.hotelapp.backend.model.entities.Guest;
import es.udc.hotelapp.backend.model.entities.GuestDao;
import es.udc.hotelapp.backend.model.entities.GuestReservation;
import es.udc.hotelapp.backend.model.entities.GuestReservationDao;
import es.udc.hotelapp.backend.model.entities.HotelDao;
import es.udc.hotelapp.backend.model.entities.Room;
import es.udc.hotelapp.backend.model.entities.RoomDao;
import es.udc.hotelapp.backend.model.entities.RoomReservation;
import es.udc.hotelapp.backend.model.entities.RoomReservationDao;
import es.udc.hotelapp.backend.model.entities.RoomTypeDao;
import es.udc.hotelapp.backend.model.entities.RoomTypeReservation;
import es.udc.hotelapp.backend.model.entities.RoomTypeReservationDao;
import es.udc.hotelapp.backend.model.entities.Status;
import es.udc.hotelapp.backend.model.exceptions.IncorrectReservationException;
import es.udc.hotelapp.backend.model.exceptions.InstanceNotFoundException;

@Service
@Transactional
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	RoomTypeDao typeDao;
	@Autowired
	RoomTypeReservationDao roomtypereservationDao;
	@Autowired
	HotelDao hotelDao;
	@Autowired
	RoomDao roomDao;
	@Autowired
	RoomReservationDao roomreservationDao;
	@Autowired
	GuestReservationDao guestReservationDao;
	@Autowired
	GuestDao guestDao;

	@Override
	public RoomTypeReservation addReservation(RoomTypeReservation rt1) throws InstanceNotFoundException {
		if (!typeDao.existsByName(rt1.getRoomtype().getName())) {
			throw new InstanceNotFoundException("project.entities.roomtype", rt1.getRoomtype().getId());
		}
		if (!hotelDao.existsByName(rt1.getHotel().getName())) {
			throw new InstanceNotFoundException("project.entities.hotel", rt1.getHotel().getId());
		}
		roomtypereservationDao.save(rt1);
		return rt1;
	}

	@Override
	public List<RoomTypeReservation> findReservations(String username) {
		List<RoomTypeReservation> result = new ArrayList<>();
		Iterable<RoomTypeReservation> reservations = roomtypereservationDao.findAll();
		for (RoomTypeReservation r : reservations) {
			if (r.getUser().getFirstName().equalsIgnoreCase(username)) {
				result.add(r);
			}
		}
		return result;
	}

	@Override
	public void updateReservation(RoomTypeReservation rt2) {
		RoomTypeReservation actual = null;
		Optional<RoomTypeReservation> roomtypereservationfound = roomtypereservationDao.findById(rt2.getId());
		if (roomtypereservationfound.isPresent()) {
			
			actual = roomtypereservationfound.get();
			if (typeDao.existsByName(rt2.getRoomtype().getName())) {
				actual.setRoomtype(rt2.getRoomtype());
			}
			actual.setRooms(rt2.getRooms());
			actual.setInbound(rt2.getInbound());
			actual.setOutbound(rt2.getOutbound());
					
		}
			
			roomtypereservationDao.save(actual);
		

	}

	@Override
	public RoomReservation assignReservation(RoomReservation rr1, Long id) throws IncorrectReservationException {
		Optional<Room> rfound = roomDao.findByNumber(rr1.getRoom().getNumber());
		Optional<RoomTypeReservation> rtr = roomtypereservationDao.findById(id);
		if (rtr.isPresent() && rfound.get().getType() == rtr.get().getRoomtype() && rfound.get().getHotel()== rtr.get().getHotel()) {
					rfound.get().setStatus(Status.OCUPADA);
					roomDao.save(rfound.get());
					roomreservationDao.save(rr1);
				} else throw new IncorrectReservationException();
	

		return rr1;
	}

	@Override
	public RoomTypeReservation findById(Long id) throws InstanceNotFoundException {
		Optional<RoomTypeReservation> reservation = roomtypereservationDao.findById(id);
		if (! reservation.isPresent()) {
			throw new InstanceNotFoundException("project.entities.roomtypereservation", id);
		}
		return reservation.get();
	}

	@Override
	public GuestReservation addGuest(GuestReservation gr1) throws IncorrectReservationException {
		if(! roomtypereservationDao.existsById(gr1.getReservation().getId())) {
			throw new IncorrectReservationException();
		}
		if(guestDao.existsByDni(gr1.getGuest().getDni())) {
			Optional<Guest> guest = guestDao.findByDni(gr1.getGuest().getDni());
			if (guest.isPresent() && guest.get().equals(gr1.getGuest())) {
				gr1.setGuest(guest.get());
			} else {
				gr1.getGuest().setId(guest.get().getId());
				guestDao.save(gr1.getGuest());
			}
		} else {
			guestDao.save(gr1.getGuest());
		}
		
		guestReservationDao.save(gr1);
		return gr1;
	}

	@Override
	public GuestReservation findGuestReservationById(Long id) throws InstanceNotFoundException {
		Optional<GuestReservation> guestreservation = guestReservationDao.findById(id);
		if(! guestreservation.isPresent()) {
			throw new InstanceNotFoundException("project.entities.guestreservation", id);
		}
		return guestreservation.get();
	}

	@Override
	public List<RoomTypeReservation> findReservationsHotel(Long id) {
		List<RoomTypeReservation> result = new ArrayList<>();
		Iterable<RoomTypeReservation> reservations = roomtypereservationDao.findAll();
		for (RoomTypeReservation r : reservations) {
			if (r.getHotel().getId() == id) {
					result.add(r);
			}
		}
		return result;
	}

	@Override
	public void updateGuest(GuestReservation gr1) throws InstanceNotFoundException {
		Optional<GuestReservation> gr = guestReservationDao.findById(gr1.getId());
		if (! gr.isPresent()) {
			throw new InstanceNotFoundException("project.entities.guestreservation", gr1.getId());
		}
		GuestReservation actual = gr.get();
		if(guestDao.existsByDni(gr1.getGuest().getDni())) {
			actual.getGuest().setName(gr1.getGuest().getName());
			actual.getGuest().setSurname(gr1.getGuest().getSurname());
			actual.getGuest().setAddress(gr1.getGuest().getAddress());
			guestDao.save(actual.getGuest());
		}
		guestReservationDao.save(actual);
		
	}


	@Override
	public List<RoomTypeReservation> findReservationHotelDate(Long id, LocalDate date) {
		List<RoomTypeReservation> result = new ArrayList<>();
		Iterable<RoomTypeReservation> reservations = roomtypereservationDao.findAll();
		for (RoomTypeReservation r : reservations) {
			if (r.getHotel().getId() == id && r.getInbound().equals(date)) {
					result.add(r);
			}
		}
		return result;
	}

	@Override
	public List<GuestReservation> findAllGuestReservation (Long hotelid){
		Iterable<GuestReservation> list = guestReservationDao.findAll();
		List<GuestReservation> result = new ArrayList<>();

		for (GuestReservation gr : list ) {
			if(gr.getReservation().getHotel().getId() == hotelid)
				result.add(gr);
		}
		return result;
	}
	
	@Override
	public void removeReservation (Long reservationid) throws InstanceNotFoundException {
	
	Optional<RoomTypeReservation> reservationfound = roomtypereservationDao.findById(reservationid);
	
		if (reservationfound.isPresent()) {
			roomtypereservationDao.delete(reservationfound.get());
		} else
			throw new InstanceNotFoundException("project.entities.rooomtypereservation", reservationid);
	}

	}
