package es.udc.hotelapp.backend.test.model.services;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import es.udc.hotelapp.backend.model.entities.Hotel;
import es.udc.hotelapp.backend.model.entities.Room;
import es.udc.hotelapp.backend.model.services.RoomService;
import es.udc.hotelapp.backend.model.entities.RoomType;
import es.udc.hotelapp.backend.model.entities.Status;
import es.udc.hotelapp.backend.model.exceptions.HotelAlreadyExistsException;
import es.udc.hotelapp.backend.model.exceptions.InstanceNotFoundException;
import es.udc.hotelapp.backend.model.exceptions.RoomAlreadyExistsException;
import es.udc.hotelapp.backend.model.services.HotelService;


@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class RoomServiceTest {
	
	@Autowired
	RoomService roomService;
	
	@Autowired
	HotelService hotelService;
	
	
	private Hotel createHotel() {
		return new Hotel("As Arias", "Pedro Gonzalez", " C/ Lonzas, 20", "981723452", "LOrem prego");
	}
	
	
	@Test
	public void testAddRoomAndFindRoom() throws HotelAlreadyExistsException, InstanceNotFoundException, RoomAlreadyExistsException {
		Hotel h1 = createHotel();
		hotelService.createHotel(h1);
		RoomType type = new RoomType("DOBLE");
		Room r1 = new Room(201,type,h1);
		
		roomService.addRoom(r1);
		Room r2 = roomService.findRoom(r1.getId());
		
		assertEquals(r1,r2);
		
	}
	
	@Test
	public void testAddExistentRoom() throws HotelAlreadyExistsException, InstanceNotFoundException, RoomAlreadyExistsException {
		Hotel h1 = createHotel();

		hotelService.createHotel(h1);
		RoomType type = new RoomType("DOBLE");
		Room r1 = new Room(201,type,h1);
		
		roomService.addRoom(r1);
		
		assertThrows(RoomAlreadyExistsException.class,() -> roomService.addRoom(r1));
		
		
	}
	
	@Test
	public void testUpdateRoom() throws HotelAlreadyExistsException, InstanceNotFoundException, RoomAlreadyExistsException {
		Hotel h1 = createHotel();
		hotelService.createHotel(h1);
		RoomType type = new RoomType("DOBLE");
		Room r1 = new Room(201,type,h1);
		
		roomService.addRoom(r1);
		
		r1.setStatus(Status.OCUPADA);
		roomService.updateRoom(r1);
		
		assertEquals(r1, roomService.findRoom(r1.getId()));
	}

	@Test
	public void testRemoveRoom() throws HotelAlreadyExistsException, InstanceNotFoundException, RoomAlreadyExistsException {
		Hotel h1 = createHotel();
		hotelService.createHotel(h1);
		
		RoomType type = new RoomType("DOBLE");
		Room r1 = new Room(201,type,h1);
		
		roomService.addRoom(r1);
		
		roomService.removeRoom(r1);
		
		assertThrows(InstanceNotFoundException.class, ()-> roomService.findRoom(r1.getId()));
		
		assertThrows(InstanceNotFoundException.class, () -> roomService.removeRoom(r1));
		
	}
	
	@Test
	public void testFindRoomsByStatus() throws HotelAlreadyExistsException, InstanceNotFoundException, RoomAlreadyExistsException {
		Hotel h1 = createHotel();
		hotelService.createHotel(h1);
		
		Hotel h2 = createHotel();
		h2.setManager("Pepe Perez");
		h2.setName("NH");
		hotelService.createHotel(h2);
		
		RoomType type = new RoomType("DOBLE");
		Room r1 = new Room(201,type,h1);
		roomService.addRoom(r1);
		Room r2 = new Room(202,type,h1);
		roomService.addRoom(r2);
		Room r3 = new Room(201,type,h2);
		roomService.addRoom(r3);
		List<Room> result = new ArrayList<>();
		result.add(r1); result.add(r2);
		
		List<Room> result1 = new ArrayList<>();
		
		assertEquals(result, roomService.findRooms(Status.LIBRE, h1.getId()));
		
		assertEquals(result1, roomService.findRooms(Status.OCUPADA, h1.getId()));
			
	}
	
	@Test
	public void testFindRoomsByHotelid() throws HotelAlreadyExistsException, InstanceNotFoundException, RoomAlreadyExistsException {
		Hotel h1 = createHotel();
		hotelService.createHotel(h1);
		
		Hotel h2 = createHotel();
		h2.setManager("Pepe Perez");
		h2.setName("NH");
		hotelService.createHotel(h2);
		
		RoomType type = new RoomType("DOBLE");
		Room r1 = new Room(201,type,h1);
		roomService.addRoom(r1);
		Room r2 = new Room(202,type,h1);
		roomService.addRoom(r2);
		Room r3 = new Room(201,type,h2);
		roomService.addRoom(r3);
		List<Room> result = new ArrayList<>();
		result.add(r1); result.add(r2);
		
		assertEquals(result, roomService.findRooms(h1.getId()));
	}
	
	@Test
	public void testFindAllRoomTypes() throws HotelAlreadyExistsException, InstanceNotFoundException, RoomAlreadyExistsException {
		
		List<RoomType> list = new ArrayList<>();
		
		Hotel h1 = createHotel();
		hotelService.createHotel(h1);
		RoomType type1 = new RoomType("DOBLE");
		RoomType type2 = new RoomType("ESEPCIAL");
		Room r1 = new Room(201,type1,h1);
		roomService.addRoom(r1);
		Room r2 = new Room(202,type2,h1);
		roomService.addRoom(r2);
		
		list.add(type1); list.add(type2);
		
		assertEquals(list, roomService.findAllRoomTypes());
		
	}
}
