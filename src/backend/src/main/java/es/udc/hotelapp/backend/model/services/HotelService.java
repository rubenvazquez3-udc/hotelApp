package es.udc.hotelapp.backend.model.services;

import java.util.List;

import es.udc.hotelapp.backend.model.entities.Hotel;
import es.udc.hotelapp.backend.model.entities.Product;
import es.udc.hotelapp.backend.model.entities.RoomTypePrice;
import es.udc.hotelapp.backend.model.entities.Service;
import es.udc.hotelapp.backend.model.exceptions.HotelAlreadyExistsException;
import es.udc.hotelapp.backend.model.exceptions.InstanceNotFoundException;
import es.udc.hotelapp.backend.model.exceptions.ProductAlreadyExistsException;
import es.udc.hotelapp.backend.model.exceptions.ServiceAlreadyExistsException;

public interface HotelService {
	public Long createHotel(Hotel hotel) throws HotelAlreadyExistsException;

	public Long addService(Service service)
			throws InstanceNotFoundException, ServiceAlreadyExistsException;
	
	public Service findService(Long id) throws InstanceNotFoundException;
	
	public Block<Service> findServices(Long hotelid);

	public Hotel findById(Long id) throws InstanceNotFoundException;

	public Hotel updateHotel(Hotel hotel) throws InstanceNotFoundException;
	
	public Service updateService (Service service1) throws InstanceNotFoundException;

	public void removeHotel(Long hotelid) throws InstanceNotFoundException;
	
	public void removeService(Long serviceId) throws InstanceNotFoundException;
	
	public List<Hotel> findHotels();
	
	public Long addPrice( RoomTypePrice price);
	
	public RoomTypePrice updateRoomTypePrice ( RoomTypePrice price) throws InstanceNotFoundException;
	
	public RoomTypePrice findPriceById( Long id);
	
	public Long addProduct (Product p) throws InstanceNotFoundException, ProductAlreadyExistsException;
	
	public Product findProduct( Long id) throws InstanceNotFoundException;
	
	public Block<Product> findProducts(Long hotelid) throws InstanceNotFoundException;
	
	public Product updateProduct ( Product p) throws InstanceNotFoundException;
	
	public void removeProduct (Long productid) throws InstanceNotFoundException;
	

}
