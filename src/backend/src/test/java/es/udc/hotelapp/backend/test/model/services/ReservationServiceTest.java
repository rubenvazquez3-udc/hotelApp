package es.udc.hotelapp.backend.test.model.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import es.udc.hotelapp.backend.model.entities.Account;
import es.udc.hotelapp.backend.model.entities.Guest;
import es.udc.hotelapp.backend.model.entities.GuestReservation;
import es.udc.hotelapp.backend.model.entities.Hotel;
import es.udc.hotelapp.backend.model.entities.HotelDao;
import es.udc.hotelapp.backend.model.entities.Product;
import es.udc.hotelapp.backend.model.entities.Room;
import es.udc.hotelapp.backend.model.entities.RoomDao;
import es.udc.hotelapp.backend.model.entities.RoomReservation;
import es.udc.hotelapp.backend.model.entities.RoomReservationDao;
import es.udc.hotelapp.backend.model.entities.RoomType;
import es.udc.hotelapp.backend.model.entities.RoomTypeDao;
import es.udc.hotelapp.backend.model.entities.RoomTypePrice;
import es.udc.hotelapp.backend.model.entities.RoomTypeReservation;
import es.udc.hotelapp.backend.model.entities.RoomTypeReservationDao;
import es.udc.hotelapp.backend.model.entities.Service;
import es.udc.hotelapp.backend.model.entities.Status;
import es.udc.hotelapp.backend.model.entities.User;
import es.udc.hotelapp.backend.model.entities.UserDao;
import es.udc.hotelapp.backend.model.exceptions.IncorrectReservationException;
import es.udc.hotelapp.backend.model.exceptions.InstanceNotFoundException;
import es.udc.hotelapp.backend.model.exceptions.PermissionException;
import es.udc.hotelapp.backend.model.exceptions.ProductAlreadyExistsException;
import es.udc.hotelapp.backend.model.exceptions.ServiceAlreadyExistsException;
import es.udc.hotelapp.backend.model.services.Block;
import es.udc.hotelapp.backend.model.services.HotelService;
import es.udc.hotelapp.backend.model.services.ReservationService;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class ReservationServiceTest {
	
	@Autowired
	ReservationService reservationService;
	@Autowired
	HotelService hotelService;
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
		
		return new RoomTypeReservation(us1, LocalDate.parse("2021-07-10"), LocalDate.parse("2021-07-11"),1, rt1,h1);
	}
	
	
	@Test
	public void testAddFindByIdReservation() throws InstanceNotFoundException, PermissionException {
		
		RoomTypeReservation rt1 = create("username","DOUBLE");
		
		RoomTypeReservation rt3 = new RoomTypeReservation(rt1.getUser(),rt1.getInbound(),rt1.getOutbound().plusDays(2), 1, rt1.getRoomtype(),rt1.getHotel());
		
		RoomTypeReservation rt4 = new RoomTypeReservation(rt1.getUser(),LocalDate.parse("2021-07-10"),LocalDate.parse("2021-07-11"), 1, rt1.getRoomtype(),rt1.getHotel());
		RoomTypeReservation rt5 = new RoomTypeReservation(rt1.getUser(),LocalDate.parse("2021-07-10"),LocalDate.parse("2021-07-11"), 1, rt1.getRoomtype(),rt1.getHotel());
		RoomTypeReservation rt6 = new RoomTypeReservation(rt1.getUser(),LocalDate.parse("2021-07-10"),LocalDate.parse("2021-07-11"), 1, rt1.getRoomtype(),rt1.getHotel());
		RoomTypeReservation rt7 = new RoomTypeReservation(rt1.getUser(),LocalDate.parse("2021-07-10"),LocalDate.parse("2021-07-11"), 1, rt1.getRoomtype(),rt1.getHotel());
		RoomTypeReservation rt8 = new RoomTypeReservation(rt1.getUser(),LocalDate.parse("2021-07-10"),LocalDate.parse("2021-07-11"), 1, rt1.getRoomtype(),rt1.getHotel());
		RoomTypeReservation rt9 = new RoomTypeReservation(rt1.getUser(),LocalDate.parse("2021-07-10"),LocalDate.parse("2021-07-11"), 1, rt1.getRoomtype(),rt1.getHotel());
		RoomTypeReservation rt10 = new RoomTypeReservation(rt1.getUser(),LocalDate.parse("2021-07-10"),LocalDate.parse("2021-07-11"), 1, rt1.getRoomtype(),rt1.getHotel());
		RoomTypeReservation rt11 = new RoomTypeReservation(rt1.getUser(),LocalDate.parse("2021-07-10"),LocalDate.parse("2021-07-11"), 1, rt1.getRoomtype(),rt1.getHotel());
		
		
		Room r1 = new Room(202, rt1.getRoomtype(), rt1.getHotel());
		Room r2 = new Room(212,Status.NO_UTILIZABLE, rt1.getRoomtype(), rt1.getHotel());
		Room r3 = new Room(222, rt1.getRoomtype(), rt1.getHotel());
		Room r4 = new Room(102, rt1.getRoomtype(), rt1.getHotel());
		Room r5 = new Room(103, rt1.getRoomtype(), rt1.getHotel());
		Room r6 = new Room(104, rt1.getRoomtype(), rt1.getHotel());
		
		roomDao.save(r1);
		roomDao.save(r2);
		roomDao.save(r3);
		
		reservationService.addReservation(rt1);
		reservationService.addReservation(rt3);
		
		assertEquals(rt1, reservationService.findById(rt1.getId())); //Añade Correctamente
		assertEquals(rt3, reservationService.findById(rt3.getId()));
		
		assertThrows(PermissionException.class, () -> reservationService.addReservation(rt4)); //Overbooking
		
		roomDao.save(r4);
		roomDao.save(r5);
		roomDao.save(r6);
		roomDao.save(new Room(105, rt1.getRoomtype(), rt1.getHotel()));
		roomDao.save(new Room(106, rt1.getRoomtype(), rt1.getHotel()));
		roomDao.save(new Room(107, rt1.getRoomtype(), rt1.getHotel()));
		roomDao.save(new Room(108, rt1.getRoomtype(), rt1.getHotel()));
		roomDao.save(new Room(109, rt1.getRoomtype(), rt1.getHotel()));
		roomDao.save(new Room(110, rt1.getRoomtype(), rt1.getHotel()));
		roomDao.save(new Room(111, rt1.getRoomtype(), rt1.getHotel()));
		roomDao.save(new Room(112, rt1.getRoomtype(), rt1.getHotel()));
		roomDao.save(new Room(113, rt1.getRoomtype(), rt1.getHotel()));
		
		reservationService.addReservation(rt4); 
		assertEquals(rt4, reservationService.findById(rt4.getId())); //Añade Correctamente
		reservationService.addReservation(rt5);
		assertEquals(rt5, reservationService.findById(rt5.getId())); //Añade Correctamente
		reservationService.addReservation(rt6);
		assertEquals(rt6, reservationService.findById(rt6.getId())); //Añade Correctamente
		reservationService.addReservation(rt7);
		assertEquals(rt7, reservationService.findById(rt7.getId())); //Añade Correctamente
		reservationService.addReservation(rt8);
		assertEquals(rt8, reservationService.findById(rt8.getId())); //Añade Correctamente
		reservationService.addReservation(rt9);
		assertEquals(rt9, reservationService.findById(rt9.getId())); //Añade Correctamente
		reservationService.addReservation(rt10);
		assertEquals(rt10, reservationService.findById(rt10.getId())); //Añade Correctamente
		reservationService.addReservation(rt11);
		assertEquals(rt11, reservationService.findById(rt11.getId())); //Añade Correctamente
		
		

		long id3 = rt11.getId() + 2;
		
		assertThrows(InstanceNotFoundException.class, () -> reservationService.findById(id3));
		
		RoomTypeReservation rt2 = new RoomTypeReservation();
		Hotel hotel = new Hotel();
		rt2.setUser(rt1.getUser());
		rt2.setHotel(hotel);
		rt2.setRoomtype(new RoomType("Ind"));
		
		assertThrows(InstanceNotFoundException.class, () -> reservationService.addReservation(rt2));
		
		hotel.setName("Panama");
		rt2.setRoomtype(rt1.getRoomtype());
		rt2.setHotel(hotel);
		assertThrows(InstanceNotFoundException.class, () -> reservationService.addReservation(rt2));
		
		
	}

	@Test
	public void testUpdateReservation() throws InstanceNotFoundException, PermissionException{
		RoomTypeReservation rt1 = create("username","DOUBLE");
		
		Room r1 = new Room(202, rt1.getRoomtype(), rt1.getHotel());
		Room r2 = new Room(212,Status.NO_UTILIZABLE, rt1.getRoomtype(), rt1.getHotel());
		Room r3 = new Room(222, rt1.getRoomtype(), rt1.getHotel());
		roomDao.save(r1);
		roomDao.save(r2);
		roomDao.save(r3);
		
		reservationService.addReservation(rt1);
		RoomType t1=new RoomType("INDIVIDUAL");
		typeDao.save(t1);
		
		rt1.setRooms(2);
		
		rt1.setRoomtype(t1);
		reservationService.updateReservation(rt1);
		RoomTypeReservation rt2 = reservationService.findById(rt1.getId());
		
		assertEquals(rt1, rt2);
	}
	
	@Test
	public void testFindReservations() throws InstanceNotFoundException, PermissionException {
		RoomTypeReservation rt1 = create("username","DOUBLE");
		RoomTypeReservation rt2 = create("mongolo","INDIVIDUAL");
		rt2.setUser(rt1.getUser());
		rt2.setRooms(1);
		List<RoomTypeReservation> result = new ArrayList<>();
		List<RoomTypeReservation> result2 = new ArrayList<>();
		Room r1 = new Room(202, rt1.getRoomtype(), rt1.getHotel());
		Room r2 = new Room(212,Status.NO_UTILIZABLE, rt1.getRoomtype(), rt1.getHotel());
		Room r3 = new Room(222, rt2.getRoomtype(), rt2.getHotel());
		roomDao.save(r1);
		roomDao.save(r2);
		roomDao.save(r3);
		reservationService.addReservation(rt1);
		reservationService.addReservation(rt2);
		result.add(rt1);
		result.add(rt2);
		
		assertEquals(result, reservationService.findReservations(null,"","firstName"));
		assertEquals(result2, reservationService.findReservations(null,"","lastName"));
	}
	
	@Test
	public void testassignRoom() throws InstanceNotFoundException, IncorrectReservationException, PermissionException {
		RoomTypeReservation rt1 = create("username", "DOUBLE");
		Room r1 = new Room(202, rt1.getRoomtype(), rt1.getHotel());
		Room r2 = new Room(212,Status.NO_UTILIZABLE, rt1.getRoomtype(), rt1.getHotel());
		Room r3 = new Room(222, rt1.getRoomtype(), rt1.getHotel());
		roomDao.save(r1);
		roomDao.save(r2);
		roomDao.save(r3);
		reservationService.addReservation(rt1);
		Room r4 = new Room(201,Status.LIBRE,rt1.getRoomtype(),rt1.getHotel());
		roomDao.save(r4);
		
		RoomReservation rr1 = new RoomReservation();
		rr1.setReservation(rt1);
		rr1.setRoom(r4);
		rr1.setBegin(rt1.getInbound());
		rr1.setEnd(rt1.getOutbound());
		
		assertEquals(rr1, reservationService.assignReservation(rr1, rt1.getId())); // Correctamente asignando
		
		
		RoomType type = new RoomType("Individual");
		Hotel h1 = new Hotel("NH", "Manolo", "C/Lonzas 20", "981723452", "LOrem prego");
		hotelDao.save(h1);
		typeDao.save(type);
		
		Room r5 = new Room(205, Status.LIBRE, type, rt1.getHotel());
		roomDao.save(r5);
		
		Room r6 = new Room(203, Status.LIBRE, rt1.getRoomtype(), h1);
		roomDao.save(r6);
		
		rr1.setRoom(r5); //Type diferente
		assertThrows(IncorrectReservationException.class, () -> reservationService.assignReservation(rr1, rt1.getId()));
		rr1.setRoom(r6); // Hotel diferente
		assertThrows(IncorrectReservationException.class, () -> reservationService.assignReservation(rr1, rt1.getId()));
		
		assertThrows(IncorrectReservationException.class, () -> reservationService.assignReservation(rr1, (long) 7));
		
		
		
		
	}
	
	@Test
	public void testAddguest() throws InstanceNotFoundException, IncorrectReservationException, PermissionException {
		RoomTypeReservation rt1 = create("username", "DOUBLE");
		
		Room r1 = new Room(202, rt1.getRoomtype(), rt1.getHotel());
		Room r2 = new Room(212,Status.NO_UTILIZABLE, rt1.getRoomtype(), rt1.getHotel());
		Room r3 = new Room(222, rt1.getRoomtype(), rt1.getHotel());
		roomDao.save(r1);
		roomDao.save(r2);
		roomDao.save(r3);
		
		reservationService.addReservation(rt1);
		
		Guest g1 = new Guest("Pepe", "Perez", "34567821A", "La Coruña", "984763827");
		
		GuestReservation gr1 = new GuestReservation(rt1, g1);
		
		reservationService.addGuest(gr1);
		
		assertEquals(gr1, reservationService.findGuestReservationById(gr1.getId()));
		
		GuestReservation gr2 = new GuestReservation(rt1, g1);
		
		reservationService.addGuest(gr2);
		assertEquals(gr2, reservationService.findGuestReservationById(gr2.getId()));
		
		long id3 = gr2.getId() + 3;
		
		assertThrows(InstanceNotFoundException.class, () -> reservationService.findGuestReservationById(id3));
	
		RoomTypeReservation rt2 = new RoomTypeReservation();
		rt2.setId(id3+9);
		GuestReservation gr3 = new GuestReservation(rt2, g1);
		
		assertThrows(IncorrectReservationException.class, () -> reservationService.addGuest(gr3));
		
		g1.setName("Manolo");
		g1.setAddress("Lugo");
		
		GuestReservation gr4 = new GuestReservation(rt1, g1);
		
		reservationService.addGuest(gr4);
		
		assertEquals(gr4, reservationService.findGuestReservationById(gr4.getId()));
		
	}
	
	@Test
	public void testFindReservationsHotel() throws InstanceNotFoundException, PermissionException {
		List<RoomTypeReservation> list = new ArrayList<>();
		List<RoomTypeReservation> list2 = new ArrayList<>();
		RoomTypeReservation rt1 = create("username","DOUBLE");
		
		Room r1 = new Room(202, rt1.getRoomtype(), rt1.getHotel());
		Room r2 = new Room(212,Status.NO_UTILIZABLE, rt1.getRoomtype(), rt1.getHotel());
		Room r3 = new Room(222, rt1.getRoomtype(), rt1.getHotel());
		roomDao.save(r1);
		roomDao.save(r2);
		roomDao.save(r3);
		
		reservationService.addReservation(rt1);
		list.add(rt1);
		
		assertEquals(list, reservationService.findReservations(rt1.getHotel().getId(),"",""));
		
		assertEquals(list, reservationService.findReservations(rt1.getHotel().getId(), rt1.getInbound().toString(),""));
	
		assertEquals(list2, reservationService.findReservations((long) 8, rt1.getInbound().toString(),""));
		
		assertEquals(list2, reservationService.findReservations(rt1.getHotel().getId(), "2020-01-01",""));
		
		assertEquals(list2, reservationService.findReservations((long) 3,"",""));
		
		assertEquals(list, reservationService.findReservations(rt1.getHotel().getId(), rt1.getUser().getId(), rt1.getInbound().toString()));
		
	}
	
	@Test
	public void testRemoveReservation() throws InstanceNotFoundException, PermissionException {
		RoomTypeReservation rt1 = create("username","DOUBLE");
		
		Room r1 = new Room(202, rt1.getRoomtype(), rt1.getHotel());
		Room r2 = new Room(212,Status.NO_UTILIZABLE, rt1.getRoomtype(), rt1.getHotel());
		Room r3 = new Room(222, rt1.getRoomtype(), rt1.getHotel());
		roomDao.save(r1);
		roomDao.save(r2);
		roomDao.save(r3);
		
		reservationService.addReservation(rt1);
		
		reservationService.removeReservation(rt1.getId());
		
		assertThrows(InstanceNotFoundException.class, () -> reservationService.findById(rt1.getId()));
		
		assertThrows(InstanceNotFoundException.class, () -> reservationService.removeReservation(rt1.getId()+3));
		
	}
	
	@Test
	public void testCreateAccount() throws InstanceNotFoundException, PermissionException, IncorrectReservationException {
		
		RoomTypeReservation rt1 = create("username","DOUBLE");
		RoomTypeReservation rt3 = new RoomTypeReservation(rt1.getUser(),rt1.getInbound(),rt1.getOutbound().plusDays(2), 1, rt1.getRoomtype(),rt1.getHotel());
		
		Room r1 = new Room(202, rt1.getRoomtype(), rt1.getHotel());
		
		
		RoomTypePrice price = new RoomTypePrice(rt1.getHotel(),rt1.getRoomtype(), new BigDecimal(55.0));
		roomDao.save(r1);
		reservationService.addReservation(rt1);
		hotelService.addPrice(price);

		Account acc = new Account(rt1);
		reservationService.createAccount(acc);
		assertEquals(acc, reservationService.findAccount(rt1.getId()));
		
		RoomType t1=new RoomType("INDIVIDUAL");
		typeDao.save(t1);
		
		Room r2 = new Room(203, t1, rt1.getHotel());
		roomDao.save(r2);
		
		RoomTypeReservation rt4 = new RoomTypeReservation(rt1.getUser(),rt1.getInbound(),rt1.getOutbound().plusDays(2), 1, t1,rt1.getHotel());
		
		reservationService.addReservation(rt4);
		
		Account acc2 = new Account(rt4);
		reservationService.createAccount(acc2);
		assertEquals(acc2, reservationService.findAccount(rt4.getId()));
				
		Account acc1 = new Account();
		rt3.setId((long) 6);
		acc1.setReservation(rt3);
		
		assertThrows(IncorrectReservationException.class, ()-> reservationService.createAccount(acc1));
		
	}
	
	@Test
	public void testFindAllGuestReservations() throws InstanceNotFoundException, PermissionException, IncorrectReservationException {
		RoomTypeReservation rt1 = create("username", "DOUBLE");
		
		Room r1 = new Room(202, rt1.getRoomtype(), rt1.getHotel());
		Room r2 = new Room(212,Status.NO_UTILIZABLE, rt1.getRoomtype(), rt1.getHotel());
		Room r3 = new Room(222, rt1.getRoomtype(), rt1.getHotel());
		roomDao.save(r1);
		roomDao.save(r2);
		roomDao.save(r3);
		
		reservationService.addReservation(rt1);
		
		Guest g1 = new Guest("Pepe", "Perez", "34567821A", "La Coruña", "984763827");
		
		GuestReservation gr1 = new GuestReservation(rt1, g1);
		
		reservationService.addGuest(gr1);
		
		Block<GuestReservation> result = new Block<>(Arrays.asList(gr1),false);
		
		assertEquals(result, reservationService.findAllGuestReservation(rt1.getHotel().getId(), "", 0, 1));
	}
	
	@Test
	public void testAddtoAccount() throws InstanceNotFoundException, PermissionException, IncorrectReservationException, ProductAlreadyExistsException, ServiceAlreadyExistsException {
		RoomTypeReservation rt1 = create("username","DOUBLE");
		
		RoomType t1=new RoomType("INDIVIDUAL");
		typeDao.save(t1);
		
		Room r2 = new Room(203, t1, rt1.getHotel());
		roomDao.save(r2);
		
		RoomTypeReservation rt4 = new RoomTypeReservation(rt1.getUser(),rt1.getInbound(),rt1.getOutbound().plusDays(2), 1, t1,rt1.getHotel());
		
		reservationService.addReservation(rt4);
		
		Account acc2 = new Account(rt4);
		reservationService.createAccount(acc2);
		//String name, String description, Double price, Hotel hotel
		Product p1 = new Product("Peras", "Variedad Conferencia", 2.0,rt4.getHotel());
		
		hotelService.addProduct(p1);
		
		Service s1 = new Service("Parking", "Aparcacoches", 3.5, rt4.getHotel());
		
		hotelService.addService(s1);
		
		reservationService.addToAccount(null, p1.getId(), rt4.getId(), 2); //Añadimos Producto a Cuenta
		
		assertEquals(2, acc2.getItem(p1.getName()).get().getQuantity());
		
		reservationService.addToAccount(null, p1.getId(), rt4.getId(), 2); //Añadimos Producto incrementa a cantidad
		
		assertEquals(4, acc2.getItem(p1.getName()).get().getQuantity());
		
		p1.setPrice(3.0);
		
		hotelService.updateProduct(p1);
		
		reservationService.addToAccount(null, p1.getId(), rt4.getId(), 2); //Añadimos Producto a Cuenta
		
		assertEquals(4, acc2.getItem(p1.getName()).get().getQuantity());
		
		
		
		reservationService.addToAccount(s1.getId(), null, rt4.getId(), 1);
		
		assertEquals(1, acc2.getItem(s1.getName()).get().getQuantity());
		
		reservationService.addToAccount(s1.getId(), null, rt4.getId(), 1);
		
		assertEquals(2, acc2.getItem(s1.getName()).get().getQuantity());
		
		s1.setPrice(4.0);
		
		hotelService.updateService(s1);
		
		reservationService.addToAccount(s1.getId(), null, rt4.getId(), 1);
		
		assertEquals(1, acc2.getItem(s1.getName()).get().getQuantity());
		
		
		assertEquals(acc2.getItems(), reservationService.findAccount(rt4.getId()).getItems());
		
		assertEquals(acc2, reservationService.findAccount(rt4.getId()));
		
		assertThrows(InstanceNotFoundException.class, ()-> reservationService.addToAccount(null, (long) 5, rt4.getId(), 3));
		
		assertThrows(InstanceNotFoundException.class, ()-> reservationService.addToAccount( (long) 5,null, rt4.getId(), 3));
		
				

	}
	
}
