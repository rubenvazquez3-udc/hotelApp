package es.udc.hotelapp.backend.model.services;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.hotelapp.backend.model.entities.Account;
import es.udc.hotelapp.backend.model.entities.AccountDao;
import es.udc.hotelapp.backend.model.entities.AccountItem;
import es.udc.hotelapp.backend.model.entities.AccountItemDao;
import es.udc.hotelapp.backend.model.entities.Guest;
import es.udc.hotelapp.backend.model.entities.GuestDao;
import es.udc.hotelapp.backend.model.entities.GuestReservation;
import es.udc.hotelapp.backend.model.entities.GuestReservationDao;
import es.udc.hotelapp.backend.model.entities.HotelDao;
import es.udc.hotelapp.backend.model.entities.Product;
import es.udc.hotelapp.backend.model.entities.ProductDao;
import es.udc.hotelapp.backend.model.entities.Room;
import es.udc.hotelapp.backend.model.entities.RoomDao;
import es.udc.hotelapp.backend.model.entities.RoomReservation;
import es.udc.hotelapp.backend.model.entities.RoomReservationDao;
import es.udc.hotelapp.backend.model.entities.RoomTypeDao;
import es.udc.hotelapp.backend.model.entities.RoomTypePrice;
import es.udc.hotelapp.backend.model.entities.RoomTypePriceDao;
import es.udc.hotelapp.backend.model.entities.RoomTypeReservation;
import es.udc.hotelapp.backend.model.entities.RoomTypeReservationDao;
import es.udc.hotelapp.backend.model.entities.ServiceDao;
import es.udc.hotelapp.backend.model.entities.Status;
import es.udc.hotelapp.backend.model.exceptions.IncorrectReservationException;
import es.udc.hotelapp.backend.model.exceptions.InstanceNotFoundException;
import es.udc.hotelapp.backend.model.exceptions.PermissionException;

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
	@Autowired
	AccountDao accDao;
	@Autowired
	AccountItemDao itemDao;
	@Autowired
	RoomTypePriceDao priceDao;
	@Autowired
	ProductDao pDao;
	@Autowired
	ServiceDao sDao;
	@Autowired
	PermissionChecker permisionchecker;

	@Override
	public RoomTypeReservation addReservation(RoomTypeReservation rt1) throws InstanceNotFoundException, PermissionException {
		if (!typeDao.existsByName(rt1.getRoomtype().getName())) {
			throw new InstanceNotFoundException("project.entities.roomtype", rt1.getRoomtype().getId());
		}
		if (!hotelDao.existsByName(rt1.getHotel().getName())) {
			throw new InstanceNotFoundException("project.entities.hotel", rt1.getHotel().getId());
		}
		
		if(! permisionchecker.checkIfPossibleToBook(rt1.getHotel().getId(), rt1.getInbound().toString(), rt1.getOutbound().toString(), rt1.getRoomtype().getName(), rt1.getRooms())) {
			throw new PermissionException();
		}
		roomtypereservationDao.save(rt1);
		return rt1;
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

	}

	@Override
	public RoomReservation assignReservation(RoomReservation rr1, Long id) throws IncorrectReservationException {
		Optional<Room> rfound = roomDao.findByNumber(rr1.getRoom().getNumber());
		Optional<RoomTypeReservation> rtr = roomtypereservationDao.findById(id);
		if (rtr.isPresent() && rfound.get().getType() == rtr.get().getRoomtype()
				&& rfound.get().getHotel() == rtr.get().getHotel()) {
			rfound.get().setStatus(Status.OCUPADA);

			roomreservationDao.save(rr1);
		} else
			throw new IncorrectReservationException();

		return rr1;
	}

	@Override
	public RoomTypeReservation findById(Long id) throws InstanceNotFoundException {
		Optional<RoomTypeReservation> reservation = roomtypereservationDao.findById(id);
		if (!reservation.isPresent()) {
			throw new InstanceNotFoundException("project.entities.roomtypereservation", id);
		}
		return reservation.get();
	}

	@Override
	public GuestReservation addGuest(GuestReservation gr1) throws IncorrectReservationException {
		if (!roomtypereservationDao.existsById(gr1.getReservation().getId())) {
			throw new IncorrectReservationException();
		}
		gr1.getGuest().setName(gr1.getGuest().getName().toUpperCase());

		if (guestDao.existsByDni(gr1.getGuest().getDni())) {
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
		if (!guestreservation.isPresent()) {
			throw new InstanceNotFoundException("project.entities.guestreservation", id);
		}
		return guestreservation.get();
	}

	@Override
	public List<RoomTypeReservation> findReservations(Long id, String date, String username) {

		List<RoomTypeReservation> result = roomtypereservationDao.find(id, username, date);
		return result;
	}

	@Override
	public Block<GuestReservation> findAllGuestReservation(Long hotelid, String username, int page, int size) {

		Slice<GuestReservation> result = guestReservationDao.find(hotelid, username, page,size);

		return new Block<>(result.getContent(),result.hasNext());
	}

	@Override
	public void removeReservation(Long reservationid) throws InstanceNotFoundException {

		Optional<RoomTypeReservation> reservationfound = roomtypereservationDao.findById(reservationid);

		if (reservationfound.isPresent()) {
			roomtypereservationDao.delete(reservationfound.get());
		} else
			throw new InstanceNotFoundException("project.entities.rooomtypereservation", reservationid);
	}

	@Override
	public Account createAccount(Account acc) throws IncorrectReservationException {

		if (!roomtypereservationDao.existsById(acc.getReservation().getId())) {
			throw new IncorrectReservationException();
		}

		accDao.save(acc);

		Optional<RoomTypePrice> price = priceDao.findByTypeIdAndHotelId(acc.getReservation().getRoomtype().getId(),
				acc.getReservation().getHotel().getId());
		if (price.isPresent()) {

			long days = Duration.between(acc.getReservation().getInbound().atStartOfDay(), acc.getReservation().getOutbound().atStartOfDay()).toDays();
			AccountItem item = new AccountItem(acc,(int) days,price.get().getPrice(),
					"Estancia en habitacion " + acc.getReservation().getRoomtype().getName());
			
			itemDao.save(item);
			acc.addItem(item);
		}

		return acc;
	}

	@Override
	public Account findAccount(Long reservationid) {
		return accDao.findByReservationId(reservationid).get() ;
	}

	private Account addServiceToAccount(Account acc, Long serviceid , int quantity) throws InstanceNotFoundException {

		Optional<es.udc.hotelapp.backend.model.entities.Service> sfound = sDao.findById(serviceid);

		if (!sfound.isPresent())
			throw new InstanceNotFoundException("project.entities.service", serviceid);
		
		Optional<AccountItem> existingitem = acc.getItem(sfound.get().getName());

		if (existingitem.isPresent() && existingitem.get().getItemPrice().equals(new BigDecimal(sfound.get().getPrice()))) {
			existingitem.get().setQuantity(existingitem.get().getQuantity() + quantity);
		} else {
			AccountItem item = new AccountItem(acc, quantity, new BigDecimal(sfound.get().getPrice()), sfound.get().getName());
			itemDao.save(item);

			acc.addItem(item);
		}

		return acc;
	}

	private Account addProductToAccount(Account acc, Long pid, int quantity) throws InstanceNotFoundException {
		
		Optional<Product> pfound = pDao.findById(pid);
		
		if (!pfound.isPresent())
			throw new InstanceNotFoundException("project.entities.product", pid);
		
		Optional<AccountItem> existingitem = acc.getItem(pfound.get().getName());

		if (existingitem.isPresent() && existingitem.get().getItemPrice().equals(new BigDecimal(pfound.get().getPrice()))) {
			existingitem.get().setQuantity(existingitem.get().getQuantity() + quantity);
		} else {
			AccountItem item = new AccountItem(acc, quantity, new BigDecimal(pfound.get().getPrice()), pfound.get().getName());
			itemDao.save(item);

			acc.addItem(item);
		}

		return acc;
	}

	@Override
	public Account addToAccount(Long serviceid, Long productid, Long reservationid, int quantity) throws InstanceNotFoundException{
		Optional<Account> acc = accDao.findByReservationId(reservationid);

		if (productid != null)
			addProductToAccount(acc.get(), productid, quantity);

		if (serviceid != null)
			addServiceToAccount(acc.get(), serviceid, quantity);

		return acc.get();
	}

	@Override
	public List<RoomTypeReservation> findReservations(Long hotelid, Long userid, String date) {
		return roomtypereservationDao.find(hotelid, userid, date);
	}

}
