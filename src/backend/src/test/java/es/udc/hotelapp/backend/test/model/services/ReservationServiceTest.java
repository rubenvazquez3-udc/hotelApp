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
import es.udc.hotelapp.backend.model.entities.Room;
import es.udc.hotelapp.backend.model.entities.RoomDao;
import es.udc.hotelapp.backend.model.entities.RoomReservation;
import es.udc.hotelapp.backend.model.entities.RoomReservationDao;
import es.udc.hotelapp.backend.model.entities.RoomType;
import es.udc.hotelapp.backend.model.entities.RoomTypeDao;
import es.udc.hotelapp.backend.model.entities.RoomTypeReservation;
import es.udc.hotelapp.backend.model.entities.RoomTypeReservationDao;
import es.udc.hotelapp.backend.model.entities.Status;
import es.udc.hotelapp.backend.model.entities.User;
import es.udc.hotelapp.backend.model.entities.UserDao;
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
	@Autowired
	RoomTypeReservationDao rtrDao;
	
	private RoomTypeReservation create(String username, String typename) {
		
		User us1 = new User(username, "password", "firstName", "lastName", "userName" + "@" + "userName" + ".com", "C/ La Locura 20, España");
		us1.setRole(User.RoleType.USER);
		userDao.save(us1);
		
		RoomType rt1 = new RoomType(typename);
		typeDao.save(rt1);
		
		Hotel h1 = new Hotel("As Arias", "Pedro Gonzalez", " C/ Lonzas, 20","981723452", "LOrem prego");
		hotelDao.save(h1);
		
		return new RoomTypeReservation(us1, LocalDate.now(), LocalDate.now(),3, rt1,h1);
	}
	
	
	@Test
	public void testAddFindByIdReservation() throws InstanceNotFoundException {
		RoomTypeReservation rt1 = create("username","DOUBLE");
		
		RoomTypeReservation rt2 = new RoomTypeReservation();
		Hotel hotel = new Hotel();
		RoomType roomtype = new RoomType("Ind");
		
		reservationService.addReservation(rt1);
		
		rt2.setUser(rt1.getUser());
		rt2.setHotel(hotel);
		rt2.setRoomtype(roomtype);
	
		long id3 = rt1.getId() + 2;
		assertEquals(rt1, reservationService.findById(rt1.getId()));
		
		assertThrows(InstanceNotFoundException.class, () -> reservationService.findById(id3));
		
		assertThrows(InstanceNotFoundException.class, () -> reservationService.addReservation(rt2));
		
		hotel.setName("Panama");
		rt2.setRoomtype(rt1.getRoomtype());
		rt2.setHotel(hotel);
		assertThrows(InstanceNotFoundException.class, () -> reservationService.addReservation(rt2));
		
		
	}

	@Test
	public void testUpdateReservation() throws InstanceNotFoundException{
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
		rt2.setUser(rt1.getUser());
		rt2.setRooms(4);
		List<RoomTypeReservation> result = new ArrayList<>();
		List<RoomTypeReservation> result2 = new ArrayList<>();
		reservationService.addReservation(rt1);
		reservationService.addReservation(rt2);
		result.add(rt1);
		result.add(rt2);
		
		assertEquals(result, reservationService.findReservations("firstName"));
		assertEquals(result2, reservationService.findReservations("lastName"));
	}
	
	@Test
	public void testassignRoom() throws InstanceNotFoundException, IncorrectReservationException {
		RoomTypeReservation rt1 = create("username", "DOUBLE");
		reservationService.addReservation(rt1);
		Room r1 = new Room(201,Status.LIBRE,rt1.getRoomtype(),rt1.getHotel());
		roomDao.save(r1);
		
		RoomReservation rr1 = new RoomReservation();
		rr1.setReservation(rt1);
		rr1.setRoom(r1);
		rr1.setBegin(rt1.getInbound());
		rr1.setEnd(rt1.getOutbound());
		
		assertEquals(rr1, reservationService.assignReservation(rr1, rt1.getId())); // Funciona Correctamente asignando
		
		
		RoomType type = new RoomType("Individual");
		Hotel h1 = new Hotel("NH", "Manolo", "C/Lonzas 20", "981723452", "LOrem prego");
		hotelDao.save(h1);
		typeDao.save(type);
		
		Room r2 = new Room(205, Status.LIBRE, type, rt1.getHotel());
		roomDao.save(r2);
		
		Room r3 = new Room(203, Status.LIBRE, rt1.getRoomtype(), h1);
		roomDao.save(r3);
		
		rr1.setRoom(r2); //Type diferente
		assertThrows(IncorrectReservationException.class, () -> reservationService.assignReservation(rr1, rt1.getId()));
		rr1.setRoom(r3); // Hotel diferente
		assertThrows(IncorrectReservationException.class, () -> reservationService.assignReservation(rr1, rt1.getId()));
		
		assertThrows(IncorrectReservationException.class, () -> reservationService.assignReservation(rr1, (long) 7));
		
		
		
		
	}
	
	@Test
	public void testAddguest() throws InstanceNotFoundException, IncorrectReservationException {
		RoomTypeReservation rt1 = create("username", "DOUBLE");
		reservationService.addReservation(rt1);
		
		Guest g1 = new Guest("Pepe", "Perez", "34567821A", "La Coruña");
		
		GuestReservation gr1 = new GuestReservation(rt1, g1);
		
		reservationService.addGuest(gr1);
		
		assertEquals(gr1, reservationService.findGuestReservationById(gr1.getId()));
		
		GuestReservation gr2 = new GuestReservation(rt1, g1);
		
		reservationService.addGuest(gr2);
		assertEquals(gr2, reservationService.findGuestReservationById(gr2.getId()));
		
		long id3 = gr2.getId() + 3;
		
		assertThrows(InstanceNotFoundException.class, () -> reservationService.findGuestReservationById(id3));
	
		RoomTypeReservation rt2 = new RoomTypeReservation();
		rt2.setId((long) 4);
		GuestReservation gr3 = new GuestReservation(rt2, g1);
		
		assertThrows(IncorrectReservationException.class, () -> reservationService.addGuest(gr3));
		
	}
	
	@Test
	public void testFindReservationsHotel() throws InstanceNotFoundException {
		List<RoomTypeReservation> list = new ArrayList<>();
		List<RoomTypeReservation> list2 = new ArrayList<>();
		RoomTypeReservation rt1 = create("username","DOUBLE");
		
		reservationService.addReservation(rt1);
		list.add(rt1);
		
		assertEquals(list, reservationService.findReservationsHotel(rt1.getHotel().getId()));
		
		assertEquals(list, reservationService.findReservationHotelDate(rt1.getHotel().getId(), rt1.getInbound()));
	
		assertEquals(list2, reservationService.findReservationHotelDate((long) 8, rt1.getInbound()));
		
		assertEquals(list2, reservationService.findReservationHotelDate(rt1.getHotel().getId(), LocalDate.parse("2020-01-01")));
		
		assertEquals(list2, reservationService.findReservationsHotel((long) 3));
		
	}
	
	@Test
	public void testUpdateGuest() throws InstanceNotFoundException, IncorrectReservationException {
		
		RoomTypeReservation rt1 = create("username", "DOUBLE");
		reservationService.addReservation(rt1);
		
		Guest g1 = new Guest("Pepe", "Perez", "34567821A", "La Coruña");
		
		GuestReservation gr1 = new GuestReservation(rt1, g1);
		
		reservationService.addGuest(gr1);
		
		g1.setName("Manuel");
		reservationService.updateGuest(gr1);
		
		assertEquals(gr1, reservationService.findGuestReservationById(gr1.getId()));
		
		gr1.setId((long) 7);
		
		assertThrows(InstanceNotFoundException.class, () -> reservationService.updateGuest(gr1));
		
	}
}
