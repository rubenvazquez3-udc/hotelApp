package es.udc.hotelapp.backend.model.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Slice;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.stereotype.Service;

import es.udc.hotelapp.backend.model.entities.Hotel;
import es.udc.hotelapp.backend.model.entities.HotelDao;
import es.udc.hotelapp.backend.model.entities.Photo;
import es.udc.hotelapp.backend.model.entities.PhotoDao;
import es.udc.hotelapp.backend.model.entities.Product;
import es.udc.hotelapp.backend.model.entities.ProductDao;
import es.udc.hotelapp.backend.model.entities.RoomTypePrice;
import es.udc.hotelapp.backend.model.entities.RoomTypePriceDao;
import es.udc.hotelapp.backend.model.entities.ServiceDao;
import es.udc.hotelapp.backend.model.exceptions.HotelAlreadyExistsException;
import es.udc.hotelapp.backend.model.exceptions.InstanceNotFoundException;
import es.udc.hotelapp.backend.model.exceptions.ProductAlreadyExistsException;
import es.udc.hotelapp.backend.model.exceptions.ServiceAlreadyExistsException;

@Service
@Transactional
public class HotelServiceImpl implements HotelService {

	@Autowired
	private HotelDao hotelDao;
	@Autowired
	private ServiceDao serviceDao;
	@Autowired
	private ProductDao productDao;
	@Autowired 
	private RoomTypePriceDao priceDao;
	
	@Autowired
	private PhotoDao photoDao;

	private final static  String UPLOADING_DIR = "C:\\Users\\Rubén\\IdeaProjects\\hotelapp\\src\\frontend\\public\\images";
	
	@Override
	public Long createHotel(Hotel hotel) throws HotelAlreadyExistsException {

		if (hotelDao.existsByName(hotel.getName())) {
			throw new HotelAlreadyExistsException(hotel.getName());
		} else {
			hotelDao.save(hotel);
			return hotel.getId();
		}

	}

	@Override
	@Transactional(readOnly = true)
	public Hotel findById(Long id) throws InstanceNotFoundException {
		Optional<Hotel> hotel = hotelDao.findById(id);
		if (!hotel.isPresent()) {
			throw new InstanceNotFoundException("project.entities.hotel", id);
		}
		return hotel.get();
	}

	@Override
	public Hotel updateHotel(Hotel hotel) throws InstanceNotFoundException {

		Optional<Hotel> hotelfound = hotelDao.findById(hotel.getId());

		if (hotelfound.isPresent()) {
			Hotel hotelactual = hotelfound.get();
			hotelactual.setName(hotel.getName());
			hotelactual.setAddress(hotel.getAddress());
			hotelactual.setManager(hotel.getManager());
			hotelactual.setPhonenumber(hotel.getPhonenumber());
			hotelactual.setDescription(hotel.getDescription());

			return hotelactual;
		} else
			throw new InstanceNotFoundException("project.entities.hotel", hotel.getId());

	}

	@Override
	public Long addService(es.udc.hotelapp.backend.model.entities.Service service)
			throws InstanceNotFoundException, ServiceAlreadyExistsException {

		Optional<Hotel> hotel = hotelDao.findById(service.getHotel().getId());

		if (!hotel.isPresent()) {
			throw new InstanceNotFoundException("project.entities.hotel", service.getHotel().getId());
		} else {
			Optional<es.udc.hotelapp.backend.model.entities.Service> hotelservice = serviceDao
					.findByName(service.getName());
			if (hotelservice.isPresent() && hotelservice.get().getHotel().getId().equals(service.getHotel().getId())) {
				throw new ServiceAlreadyExistsException(service.getName());
			} else {
				serviceDao.save(service);
				return service.getId();
			}

		}

	}

	@Override
	public void removeHotel(Long hotelid) throws InstanceNotFoundException {
		Optional<Hotel> hotel = hotelDao.findById(hotelid);
		if (hotel.isPresent()) {
			hotelDao.delete(hotel.get());
		} else
			throw new InstanceNotFoundException("project.entities.hotel", hotelid);

	}

	@Override
	public es.udc.hotelapp.backend.model.entities.Service findService(Long id) throws InstanceNotFoundException {

		Optional<es.udc.hotelapp.backend.model.entities.Service> service1 = serviceDao.findById(id);
		if (!service1.isPresent()) {
			throw new InstanceNotFoundException("project.entities.service", id);
		}

		return service1.get();
	}

	@Override
	public Block<es.udc.hotelapp.backend.model.entities.Service> findServices(Long hotelid,String name, int page, int size) {
		
		Slice<es.udc.hotelapp.backend.model.entities.Service> slice = serviceDao.find(hotelid, name, page, size);
		
		return new Block<>(slice.getContent(), slice.hasNext());
	}

	@Override
	public es.udc.hotelapp.backend.model.entities.Service updateService(
			es.udc.hotelapp.backend.model.entities.Service service1) throws InstanceNotFoundException {
		
		Optional<es.udc.hotelapp.backend.model.entities.Service> servicefound = serviceDao.findById(service1.getId());
		
		if(!servicefound.isPresent()) {
			throw new InstanceNotFoundException("project.entities.service", service1.getId());
		}
		
		es.udc.hotelapp.backend.model.entities.Service serviceActual = servicefound.get();
		serviceActual.setName(service1.getName());
		serviceActual.setDescription(service1.getDescription());
		serviceActual.setPrice(service1.getPrice());
		
		
		return serviceActual;
	}

	@Override
	public void removeService(Long serviceId) throws InstanceNotFoundException {
		Optional<es.udc.hotelapp.backend.model.entities.Service> servicefound = serviceDao.findById(serviceId);
		
		if(! servicefound.isPresent()) {
			throw new InstanceNotFoundException("project.entities.service", serviceId);
		}
		serviceDao.delete(servicefound.get());
	}

	@Override
	public Block<Hotel> findHotels(String name, String address, int page, int size) {
		Slice<Hotel> slice = hotelDao.find(name,address, page, size);

		return new Block<>(slice.getContent(), slice.hasNext());
	}

	@Override
	public Long addPrice(RoomTypePrice price){
		
		if(hotelDao.existsByName(price.getHotel().getName())) {
			if(! priceDao.findByTypeIdAndHotelId(price.getType().getId(), price.getHotel().getId()).isPresent())
				priceDao.save(price);
		}
		
		return price.getId();
	}

	@Override
	public RoomTypePrice updateRoomTypePrice(RoomTypePrice price) throws InstanceNotFoundException {
		Optional<RoomTypePrice> pricefound = priceDao.findById(price.getId());
		
		if(!pricefound.isPresent()) {
			throw new InstanceNotFoundException("project.entities.roomtypeprice", price.getId());
		}
		
		RoomTypePrice priceActual = pricefound.get();
		
		priceActual.setPrice(price.getPrice());
		
		return priceActual;
	}

	@Override
	public Long addProduct(Product p) throws InstanceNotFoundException, ProductAlreadyExistsException {
		
		Optional<Hotel> hotel = hotelDao.findById(p.getHotel().getId());

		if (!hotel.isPresent()) {
			throw new InstanceNotFoundException("project.entities.hotel", p.getHotel().getId());
		} else {
			Optional<Product> hotelproduct = productDao.findByName(p.getName());
			if (hotelproduct.isPresent() && hotelproduct.get().getHotel().getId().equals(p.getHotel().getId())) {
				throw new ProductAlreadyExistsException(p.getName());
			} else {
				productDao.save(p);
				return p.getId();
			}

		}
	}

	@Override
	public Product findProduct(Long id) throws InstanceNotFoundException {
		
		Optional<Product> product1 = productDao.findById(id);
		
		if (!product1.isPresent()) {
			throw new InstanceNotFoundException("project.entities.product", id);
		}
		
		return product1.get();
	}

	@Override
	public Block<Product> findProducts(Long hotelid,String name, int page, int size) {
		
		Slice<Product> slice = productDao.find(hotelid, name, page, size);
		
		return new Block<>(slice.getContent(), slice.hasNext());
	}

	@Override
	public Product updateProduct(Product p) throws InstanceNotFoundException {
		Optional<Product> pfound = productDao.findById(p.getId());
		
		if(!pfound.isPresent()) {
			throw new InstanceNotFoundException("project.entities.product", p.getId());
		}
		
		Product actual = pfound.get();
		actual.setName(p.getName());
		actual.setDescription(p.getDescription());
		actual.setPrice(p.getPrice());
		
		
		return actual;
	}

	@Override
	public void removeProduct(Long productid) throws InstanceNotFoundException {
		
		Optional<Product> pfound = productDao.findById(productid);
		
		if(! pfound.isPresent()) {
			throw new InstanceNotFoundException("project.entities.product", productid);
		}
		
		productDao.delete(pfound.get());
		
	}

	@Override
	public RoomTypePrice findPriceById(Long id) {
		
		return priceDao.findById(id).get();
	}

	@Override
	public boolean uploadPhoto(MultipartFile file, Long hotelid) {
		boolean f = false;

		Optional<Hotel> hotel = hotelDao.findById(hotelid);

		if (hotel.isPresent()) {

			try {
				Photo photo = new Photo(file.getOriginalFilename(), hotel.get());

				Files.copy(file.getInputStream(), Paths.get(UPLOADING_DIR + File.separator + file.getOriginalFilename()),
						StandardCopyOption.REPLACE_EXISTING);
				f = true;
				photoDao.save(photo);
				hotel.get().addPhoto(photo);

			} catch (IOException e) {

				e.printStackTrace();
			}

		}
		return f;
	}

	@Override
	public void removePrice (Long priceid) throws InstanceNotFoundException{
		Optional<RoomTypePrice> pfound = priceDao.findById(priceid);

		if(! pfound.isPresent()) {
			throw new InstanceNotFoundException("project.entities.roomtypeprice", priceid);
		}

		priceDao.delete(pfound.get());
	}
	

}
