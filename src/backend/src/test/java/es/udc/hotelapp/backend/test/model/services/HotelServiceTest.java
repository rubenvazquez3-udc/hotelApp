package es.udc.hotelapp.backend.test.model.services;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import es.udc.hotelapp.backend.model.entities.Hotel;
import es.udc.hotelapp.backend.model.entities.Product;
import es.udc.hotelapp.backend.model.entities.RoomType;
import es.udc.hotelapp.backend.model.entities.RoomTypeDao;
import es.udc.hotelapp.backend.model.entities.RoomTypePrice;
import es.udc.hotelapp.backend.model.entities.Service;
import es.udc.hotelapp.backend.model.exceptions.HotelAlreadyExistsException;
import es.udc.hotelapp.backend.model.exceptions.InstanceNotFoundException;
import es.udc.hotelapp.backend.model.exceptions.ProductAlreadyExistsException;
import es.udc.hotelapp.backend.model.exceptions.ServiceAlreadyExistsException;
import es.udc.hotelapp.backend.model.services.Block;
import es.udc.hotelapp.backend.model.services.HotelService;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class HotelServiceTest {

	@Autowired
	HotelService hotelService;
	
	@Autowired
	RoomTypeDao typedao;
	
	private Hotel createHotel() {
		return new Hotel("As Arias", "Pedro Gonzalez", " C/ Lonzas, 20", "981723452", "LOrem prego");
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
		
		hotelService.createHotel(h1);
		
		Hotel h2 = createHotel();
		
		h2.setName("Marcadores");
		
		s1.setHotel(h1);
		
		hotelService.addService(s1);
		
		Service s2 = hotelService.findService(s1.getId());
		
		assertEquals(s1,s2);
		
		assertThrows(ServiceAlreadyExistsException.class, () -> hotelService.addService(s1));
		
		h2.setId( h1.getId()*3);
		
		Service s3 = new Service("Parking", "Aparcacoches", 23.5, h2);
		
		assertThrows(InstanceNotFoundException.class, () -> hotelService.addService(s3));
		
		Hotel h3 = createHotel();
		
		h3.setName("Manager");
		
		hotelService.createHotel(h3);
		
		Service s4 = new Service("Parking", "Aparcacoches", 23.5, h3);
		hotelService.addService(s4);
		Service s5 = hotelService.findService(s4.getId());
		
		assertEquals(s4,s5);
		
		
	}
	
	@Test
	public void testRemoveService() throws HotelAlreadyExistsException, InstanceNotFoundException, ServiceAlreadyExistsException {
		Service s1 = new Service("Parking", "Aparcacoches", 23.5, null);
		Hotel h1 = createHotel();
		
		s1.setHotel(h1);
		
		hotelService.createHotel(h1);
		hotelService.addService(s1);
		
		hotelService.removeService(s1.getId());
		
		assertThrows( InstanceNotFoundException.class, () -> hotelService.findService(s1.getId()));
		
		assertThrows(InstanceNotFoundException.class, () -> hotelService.removeService(s1.getId()));
	}
	
	@Test
	public void testFindServices() throws HotelAlreadyExistsException, InstanceNotFoundException, ServiceAlreadyExistsException {
		
		Hotel h1 = createHotel();
		hotelService.createHotel(h1);
		
		Service s1 = new Service("Parking", "Aparcacoches", 23.5, h1);
		hotelService.addService(s1);
		
		Service s2 = new Service("Catering", "Comidas", 20.5, h1);
		hotelService.addService(s2);
		
		Block<Service> slice1 = new Block<>(Arrays.asList(s2,s1), false);
		Block<Service> slice2 = new Block<>(Arrays.asList(s2), false);
		
		assertEquals(slice1, hotelService.findServices(h1.getId(),"",0,2));
		assertEquals(slice1, hotelService.findServices(h1.getId(),"ing",0,2));
		assertEquals(slice2, hotelService.findServices(h1.getId(),"Catering",0,1));
		assertEquals(new Block<>(new ArrayList<>(), false), hotelService.findServices(h1.getId(),"Platano",0,1));
	}
	
	@Test
	public void testFindProducts() throws HotelAlreadyExistsException, InstanceNotFoundException, ProductAlreadyExistsException{
		
		Hotel h1 = createHotel();
		hotelService.createHotel(h1);
		Product s1 = new Product("Manzana", "Manzana Golden calidad oro", 2.5, h1);
		hotelService.addProduct(s1);
		Product s2 = new Product("Peras", "Pera Conferencia calidad oro", 1.5, h1);
		hotelService.addProduct(s2);
		
		Block<Product> slice1 = new Block<>(Arrays.asList(s1,s2), false);

		Block<Product> slice2 = new Block<>(Arrays.asList(s1), false);
		
		assertEquals(slice1, hotelService.findProducts(h1.getId(),"",0,2));
		assertEquals(slice1, hotelService.findProducts(h1.getId(),"a",0,2));

		assertEquals(slice2, hotelService.findProducts(h1.getId(),"Manzana",0,1));

		assertEquals(new Block<>(new ArrayList<>(), false), hotelService.findProducts(h1.getId(),"Platano",0,1));
		
	}
	
	@Test
	public void testUpdateService() throws HotelAlreadyExistsException, InstanceNotFoundException, ServiceAlreadyExistsException {
		
		Hotel h1 = createHotel();
		
		hotelService.createHotel(h1);
		
		Service s1 = new Service("Parking", "Aparcacoches", 23.5, h1);
		
		hotelService.addService(s1);
		
		s1.setName("Marking");
		
		Service s2 = hotelService.updateService(s1);
		
		assertEquals(s1,s2);
		
		s2.setId((long) 4);
		
		assertThrows(InstanceNotFoundException.class, () -> hotelService.updateService(s2));
		
	}
	
	@Test
	public void testFindAllHotels() throws HotelAlreadyExistsException {
		

		Hotel h1 = createHotel();
		hotelService.createHotel(h1);
		Block<Hotel> slice1 = new Block<>(Arrays.asList(h1), false);

		assertEquals(slice1, hotelService.findHotels("","",0,1));
	}
	
	@Test
	public void testAddRoomTypePrice() throws HotelAlreadyExistsException{
		
		Hotel h1 = createHotel();
		
		RoomType type = new RoomType("DOBLE");
		
		typedao.save(type);
		
		hotelService.createHotel(h1);
		
		RoomTypePrice price = new RoomTypePrice(h1,type, new BigDecimal(55.0));
		
		hotelService.addPrice(price);
		
		assertEquals(price, hotelService.findPriceById(price.getId()));
		
	}
	
	@Test
	public void testUpdateRoomTypePrice() throws HotelAlreadyExistsException, InstanceNotFoundException {
		
		Hotel h1 = createHotel();
		
		RoomType type = new RoomType("DOBLE");
		
		typedao.save(type);
		
		hotelService.createHotel(h1);
		
		RoomTypePrice price = new RoomTypePrice(h1,type, new BigDecimal(50.0));
		
		hotelService.addPrice(price);
		
		price.setPrice(new BigDecimal(56.0));
		
		hotelService.updateRoomTypePrice(price);
		
		assertEquals(price, hotelService.findPriceById(price.getId()));
		
		price.setId(price.getId()* 5);
		
		assertThrows(InstanceNotFoundException.class, () -> hotelService.updateRoomTypePrice(price));
		
	}
	
	@Test
	public void testAddProductFindById() throws HotelAlreadyExistsException, InstanceNotFoundException, ProductAlreadyExistsException {
		
		Hotel h1 = createHotel();
		
		hotelService.createHotel(h1);
		
		Product p1 = new Product("Manzana", "Manzana Golden calidad oro", 2.5, h1);
		
		Hotel h2 = createHotel();
		
		h2.setName("Marcadores");
		
		hotelService.addProduct(p1);
		
		Product s2 = hotelService.findProduct(p1.getId());
		
		assertEquals(p1,s2);
		
		assertThrows(ProductAlreadyExistsException.class, () -> hotelService.addProduct(p1));
		
		h2.setId((long) 3);
		
		Product p2 = new Product("Manzana", "Manzana Golden calidad oro", 2.5, h2);
		
		assertThrows(InstanceNotFoundException.class, () -> hotelService.addProduct(p2));
		
		Hotel h3 = createHotel();
		
		h3.setName("Manager");
		
		hotelService.createHotel(h3);
		
		Product s4 = new Product("Manzana", "Manzana Golden calidad oro", 2.5, h3);
		hotelService.addProduct(s4);
		Product s5 = hotelService.findProduct(s4.getId());
		
		assertEquals(s4,s5);
		
		assertThrows(InstanceNotFoundException.class, () -> hotelService.findProduct((long) 16));
		
		
	}
	
	@Test
	public void testRemoveProduct() throws HotelAlreadyExistsException, InstanceNotFoundException, ProductAlreadyExistsException{
		Hotel h1 = createHotel();
		hotelService.createHotel(h1);
		
		Product p1 = new Product("Manzana", "Manzana Golden calidad oro", 2.5, h1);
		
		hotelService.addProduct(p1);
		
		hotelService.removeProduct(p1.getId());
		
		assertThrows( InstanceNotFoundException.class, () -> hotelService.findProduct(p1.getId()));
		
		assertThrows(InstanceNotFoundException.class, () -> hotelService.removeProduct(p1.getId()));
	}
	
	
	
	@Test
	public void testUpdateProduct() throws HotelAlreadyExistsException, InstanceNotFoundException, ProductAlreadyExistsException{
		
		Hotel h1 = createHotel();
		
		hotelService.createHotel(h1);
		
		Product s1 = new Product("Manzana", "Manzana Golden calidad oro", 2.5, h1);
		
		hotelService.addProduct(s1);
		
		s1.setName("Manpera");
		
		Product s2 = hotelService.updateProduct(s1);
		
		assertEquals(s1,s2);
		
		s2.setId((long) 4);
		
		assertThrows(InstanceNotFoundException.class, () -> hotelService.updateProduct(s2));
		
	}
}
