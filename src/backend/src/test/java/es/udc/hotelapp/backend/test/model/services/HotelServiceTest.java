package es.udc.hotelapp.backend.test.model.services;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import es.udc.hotelapp.backend.model.entities.Hotel;
import es.udc.hotelapp.backend.model.entities.Service;
import es.udc.hotelapp.backend.model.exceptions.HotelAlreadyExistsException;
import es.udc.hotelapp.backend.model.exceptions.InstanceNotFoundException;
import es.udc.hotelapp.backend.model.exceptions.ServiceAlreadyExistsException;
import es.udc.hotelapp.backend.model.services.Block;
import es.udc.hotelapp.backend.model.services.HotelService;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class HotelServiceTest {

	@Autowired
	HotelService hotelService;
	
	private Hotel createHotel() {
		return new Hotel("As Arias", "Pedro Gonzalez", " C/ Lonzas, 20");
	}
	
	@Test
	public void testCreateHotelAndFindById() throws HotelAlreadyExistsException, InstanceNotFoundException {
		Hotel h = createHotel();
		
		Long id = hotelService.createHotel(h);
		
		Hotel hotelFound = hotelService.findById(id);
		
		assertEquals(h, hotelFound);
		
	}
	@Test
	public void testCreateDuplicatedHotel() throws HotelAlreadyExistsException {
		Hotel h = createHotel();
		
		hotelService.createHotel(h);
		assertThrows(HotelAlreadyExistsException.class, () -> hotelService.createHotel(h));
		
	}
	
	@Test
	public void testFindNonExistentHotel() {
		assertThrows(InstanceNotFoundException.class, ()-> hotelService.findById((long) 5));
	}
	
	@Test
	public void testUpdateHotel() throws HotelAlreadyExistsException, InstanceNotFoundException {
		Hotel h1 = createHotel();
		hotelService.createHotel(h1);
		
		h1.setManager("Pablo Picasso");
		h1.setAddress("C/ Pantomima, 22");
		h1.setName("NH Hotels");
		
		Hotel hotel = hotelService.updateHotel(h1);
		
		assertEquals(h1, hotel);
	}
	
	@Test
	public void testUpdateNonExistentHotel() {
		
		Hotel h2 = new Hotel();
		h2.setId((long) 3);

		assertThrows(InstanceNotFoundException.class, ()-> hotelService.updateHotel(h2));
	}
	
	@Test
	public void testRemoveHotel() throws HotelAlreadyExistsException, InstanceNotFoundException {
		Hotel h1 = createHotel();
		hotelService.createHotel(h1);
		
		hotelService.removeHotel(h1.getId());
		
		assertThrows(InstanceNotFoundException.class, ()->hotelService.findById(h1.getId()));
	}
	
	@Test
	public void testRemoveNonExistentHotel() {
		assertThrows(InstanceNotFoundException.class, ()-> hotelService.removeHotel((long) 3));
	}
	
	@Test
	public void testAddServiceFindById() throws HotelAlreadyExistsException, InstanceNotFoundException, ServiceAlreadyExistsException {
		Service s1 = new Service("Parking", "Aparcacoches", 23.5, null);
		Hotel h1 = createHotel();
		
		s1.setHotel(h1);
		
		hotelService.createHotel(h1);
		
		hotelService.addService(s1, h1.getId());
		
		Service s2 = hotelService.findService(s1.getId());
		
		assertEquals(s1,s2);
		
		assertThrows(ServiceAlreadyExistsException.class, () -> hotelService.addService(s1, h1.getId()));
		
		assertThrows(InstanceNotFoundException.class, () -> hotelService.addService(s1, (long) 5));
		
		
	}
	
	@Test
	public void testRemoveService() throws HotelAlreadyExistsException, InstanceNotFoundException, ServiceAlreadyExistsException {
		Service s1 = new Service("Parking", "Aparcacoches", 23.5, null);
		Hotel h1 = createHotel();
		
		s1.setHotel(h1);
		
		hotelService.createHotel(h1);
		hotelService.addService(s1, h1.getId());
		
		hotelService.removeService(s1.getId());
		
		assertThrows( InstanceNotFoundException.class, () -> hotelService.findService(s1.getId()));
		
		assertThrows(InstanceNotFoundException.class, () -> hotelService.removeService(s1.getId()));
	}
	
	@Test
	public void testFindServicesByHotelId() throws HotelAlreadyExistsException, InstanceNotFoundException, ServiceAlreadyExistsException {
		
		Hotel h1 = createHotel();
		hotelService.createHotel(h1);
		Service s1 = new Service("Parking", "Aparcacoches", 23.5, h1);
		hotelService.addService(s1, h1.getId());
		Service s2 = new Service("Catering", "Comidas", 23.5, h1);
		hotelService.addService(s2, h1.getId());
		
		Block<Service> slice1 = new Block<>(Arrays.asList(s1,s2), false);
		
		assertEquals(slice1, hotelService.findServices(h1.getId()));
		
	}
	
	@Test
	public void testUpdateService() throws HotelAlreadyExistsException, InstanceNotFoundException, ServiceAlreadyExistsException {
		
		Hotel h1 = createHotel();
		
		Service s1 = new Service("Parking", "Aparcacoches", 23.5, h1);
		
		hotelService.createHotel(h1);
		
		hotelService.addService(s1, h1.getId());
		
		s1.setName("Marking");
		
		Service s2 = hotelService.updateService(s1);
		
		assertEquals(s1,s2);
		
		s2.setId((long) 4);
		
		assertThrows(InstanceNotFoundException.class, () -> hotelService.updateService(s2));
		
	}
	
	@Test
	public void testFindAllHotels() throws HotelAlreadyExistsException {
		
		List<Hotel> list = new ArrayList<>();
		Hotel h1 = createHotel();
		hotelService.createHotel(h1);
		list.add(h1);
		assertEquals(list, hotelService.findHotels());
	}
}
