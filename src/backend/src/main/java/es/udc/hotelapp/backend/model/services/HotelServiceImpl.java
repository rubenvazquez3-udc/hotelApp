package es.udc.hotelapp.backend.model.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Slice;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import es.udc.hotelapp.backend.model.entities.Hotel;
import es.udc.hotelapp.backend.model.entities.HotelDao;
import es.udc.hotelapp.backend.model.entities.ServiceDao;
import es.udc.hotelapp.backend.model.exceptions.HotelAlreadyExistsException;
import es.udc.hotelapp.backend.model.exceptions.InstanceNotFoundException;
import es.udc.hotelapp.backend.model.exceptions.ServiceAlreadyExistsException;

@Service
@Transactional
public class HotelServiceImpl implements HotelService {

	@Autowired
	private HotelDao hotelDao;
	@Autowired
	private ServiceDao serviceDao;

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
	public Long addService(es.udc.hotelapp.backend.model.entities.Service service, Long hotelid)
			throws InstanceNotFoundException, ServiceAlreadyExistsException {

		Optional<Hotel> hotel = hotelDao.findById(hotelid);

		if (!hotel.isPresent()) {
			throw new InstanceNotFoundException("project.entities.hotel", service.getHotel().getId());
		} else {
			Optional<es.udc.hotelapp.backend.model.entities.Service> hotelservice = serviceDao
					.findByName(service.getName());
			if (hotelservice.isPresent() && hotelservice.get().getHotel().getId() == hotelid) {
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
	public Block<es.udc.hotelapp.backend.model.entities.Service> findServices(Long hotelid) {
		
		Slice<es.udc.hotelapp.backend.model.entities.Service> slice = serviceDao.findByHotelId(hotelid);
		
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
		//serviceDao.save(serviceActual);
		
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
	public List<Hotel> findHotels() {
		return (List<Hotel>) hotelDao.findAll();
	}

}
