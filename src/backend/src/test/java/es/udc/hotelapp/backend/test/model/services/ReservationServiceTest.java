package es.udc.hotelapp.backend.test.model.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import es.udc.hotelapp.backend.model.entities.Guest;
import es.udc.hotelapp.backend.model.entities.GuestReservation;
import es.udc.hotelapp.backend.model.entities.Hotel;
import es.udc.hotelapp.backend.model.entities.HotelDao;
import es.udc.hotelapp.backend.model.entities.Reservation;
import es.udc.hotelapp.backend.model.entities.Room;
import es.udc.hotelapp.backend.model.entities.RoomDao;
import es.udc.hotelapp.backend.model.entities.RoomReservation;
import es.udc.hotelapp.backend.model.entities.RoomReservationDao;
import es.udc.hotelapp.backend.model.entities.RoomType;
import es.udc.hotelapp.backend.model.entities.RoomTypeDao;
import es.udc.hotelapp.backend.model.entities.RoomTypeReservation;
import es.udc.hotelapp.backend.model.entities.Status;
import es.udc.hotelapp.backend.model.entities.User;
import es.udc.hotelapp.backend.model.entities.UserDao;
import es.udc.hotelapp.backend.model.exceptions.IncorrectHotelException;
import es.udc.hotelapp.backend.model.exceptions.IncorrectReservationException;
import es.udc.hotelapp.backend.model.exceptions.InstanceNotFoundException;
import es.udc.hotelapp.backend.model.services.ReservationService;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class ReservationServiceTest {
	
	@Autowired
	ReservationService reservationService;
	
	@Autowired
	RoomTypeDao typeDao;
	@Autowired
	HotelDao hotelDao;
	@Autowired
	UserDao userDao;
	@Autowired
	RoomDao roomDao;
	@Autowired
	RoomReservationDao rrDao;
	
	private RoomTypeReservation create(String username, String typename) {
		
		User us1 = new User(username, "password", "firstName", "lastName", "userName" + "@" + "userName" + ".com", "C/ La Locura 20, España");
		us1.setRole(User.RoleType.USER);
		userDao.save(us1);
		Reservation reservation = new Reservation(us1, LocalDate.now(), LocalDate.now());
		
		RoomType rt1 = new RoomType(typename);
		typeDao.save(rt1);
		
		Hotel h1 = new Hotel("As Arias", "Pedro Gonzalez", " C/ Lonzas, 20");
		hotelDao.save(h1);
		
		return new RoomTypeReservation(reservation,3, rt1,h1);
	}
	
	private RoomReservation crateRR(RoomTypeReservation r) {
		Room r1 = new Room();
		return new RoomReservation(r1, r.getReservation(), r.getReservation().getInbound(),r.getReservation().getOutbound());
	}
	
	@Test
	public void testAddFindByIdReservation() throws InstanceNotFoundException {
		RoomTypeReservation rt1 = create("username","DOUBLE");
		
		reservationService.addReservation(rt1);
		
		assertEquals(rt1, reservationService.findById(rt1.getId()));
	}

	@Test
	public void testUpdateReservation() throws InstanceNotFoundException, IncorrectHotelException {
		RoomTypeReservation rt1 = create("username","DOUBLE");
		
		reservationService.addReservation(rt1);
		RoomType t1=new RoomType("INDIVIDUAL");
		typeDao.save(t1);
		
		rt1.setRooms(5);
		
		rt1.setRoomtype(t1);
		reservationService.updateReservation(rt1);
		RoomTypeReservation rt2 = reservationService.findById(rt1.getId());
		
		assertEquals(rt1, rt2);
	}
	
	@Test
	public void testFindReservations() throws InstanceNotFoundException {
		RoomTypeReservation rt1 = create("username","DOUBLE");
		RoomTypeReservation rt2 = create("mongolo","INDIVIDUAL");
		rt2.getReservation().setUser(rt1.getReservation().getUser());
		rt2.setRooms(4);
		List<RoomTypeReservation> result = new ArrayList<>();
		reservationService.addReservation(rt1);
		reservationService.addReservation(rt2);
		result.add(rt1);
		result.add(rt2);
		
		assertEquals(result, reservationService.findReservations("firstName"));
	}
	
	@Test
	public void testassignRoom() throws InstanceNotFoundException, IncorrectReservationException {
		RoomTypeReservation rt1 = create("username", "DOUBLE");
		reservationService.addReservation(rt1);
		RoomReservation rr1 = crateRR(rt1);
		
		rr1.getRoom().setHotel(rt1.getHotel());
		
		rr1.getRoom().setNumber(201);
		rr1.getRoom().setStatus(Status.LIBRE);		
		rr1.getRoom().setType(rt1.getRoomtype());
		roomDao.save(rr1.getRoom());
		
		reservationService.assignReservation(rr1, rt1.getId());
		
		assertEquals(rr1, rrDao.findById(rr1.getId()).get());
		
		
		
	}
	
	@Test
	public void testAddguest() throws InstanceNotFoundException, IncorrectReservationException {
		RoomTypeReservation rt1 = create("username", "DOUBLE");
		reservationService.addReservation(rt1);
		
		Guest g1 = new Guest("Pepe", "Perez", "34567821A", "La Coruña");
		
		GuestReservation gr1 = new GuestReservation(rt1, g1);
		
		reservationService.addGuest(gr1);
		
		assertEquals(gr1, reservationService.findGuestReservationById(gr1.getId()));
	}
	
	@Test
	public void testFindReservationsHotel() throws InstanceNotFoundException {
		List<RoomTypeReservation> list = new ArrayList<>();
		RoomTypeReservation rt1 = create("username","DOUBLE");
		
		reservationService.addReservation(rt1);
		list.add(rt1);
		
		assertEquals(list, reservationService.findReservationsHotel(rt1.getHotel().getId()));
		
	}
}
