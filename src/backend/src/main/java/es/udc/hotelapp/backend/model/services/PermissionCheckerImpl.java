package es.udc.hotelapp.backend.model.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.hotelapp.backend.model.exceptions.InstanceNotFoundException;
import es.udc.hotelapp.backend.model.entities.Room;
import es.udc.hotelapp.backend.model.entities.RoomDao;
import es.udc.hotelapp.backend.model.entities.RoomTypeReservation;
import es.udc.hotelapp.backend.model.entities.RoomTypeReservationDao;
import es.udc.hotelapp.backend.model.entities.User;
import es.udc.hotelapp.backend.model.entities.UserDao;

@Service
@Transactional(readOnly=true)
public class PermissionCheckerImpl implements PermissionChecker {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired 
	private RoomDao roomDao;
	
	@Autowired
	private RoomTypeReservationDao rtrDao;
	

	@Override
	public User checkUser(Long userId) throws InstanceNotFoundException {

		Optional<User> user = userDao.findById(userId);
		
		if (!user.isPresent()) {
			throw new InstanceNotFoundException("project.entities.user", userId);
		}
		
		return user.get();
		
	}
	
	private int countReservations( List<RoomTypeReservation> result) {
		int total = 0;
		
			for( RoomTypeReservation r1 : result) {
				total += r1.getRooms();
			}
		return total;
	}

	@Override
	public boolean checkIfPossibleToBook(Long hotelid, String in, String out, String type, int quantity) {
		int page = 0; 
		int acumulate = 0;
		Slice <RoomTypeReservation> slice1 = rtrDao.findConflicts(hotelid, type, in, page, 10);
		
		acumulate  += countReservations(slice1.getContent());
		
		while (slice1.hasNext()) {
			page ++;
			slice1 = rtrDao.findConflicts(hotelid, type, in, page, 10);
			acumulate += countReservations(slice1.getContent());
		}
			
		page = 0;
		
		Slice<RoomTypeReservation> slice2 = rtrDao.findBetweenDates(hotelid, type, in, out, page, 10);
		acumulate += countReservations(slice2.getContent());
		
		while (slice2.hasNext()) {
			page ++;
			slice2 =  rtrDao.findBetweenDates(hotelid, type, in, out, page, 10);
			acumulate += countReservations(slice2.getContent());
		}
		
		page = 0;
		
		Slice<Room>  rooms = roomDao.find(hotelid, "", type, page, 10);
		int roomsize = rooms.getContent().size();
		
		//Si hay más de una pagina de habitaciones
		while(rooms.hasNext()) {
			page ++;
			rooms = roomDao.find(hotelid, "", type, page, 10);
			roomsize += rooms.getContent().size();
		}
		
		page = 0;
		 
		Slice<Room> notusable= roomDao.find(hotelid, "NO_UTILIZABLE", type, page, 10);
		int nousable = notusable.getContent().size();
		
		while(notusable.hasNext()) {
			page ++;
			notusable = roomDao.find(hotelid, "NO_UTILIZABLE", type, page, 10);
			nousable += notusable.getContent().size();
		}
		
		
		return roomsize - nousable >= quantity+acumulate;
	}

	

}
