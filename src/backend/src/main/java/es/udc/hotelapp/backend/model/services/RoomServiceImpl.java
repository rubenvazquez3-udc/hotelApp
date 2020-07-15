package es.udc.hotelapp.backend.model.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.hotelapp.backend.model.entities.HotelDao;
import es.udc.hotelapp.backend.model.entities.Room;
import es.udc.hotelapp.backend.model.entities.RoomDao;
import es.udc.hotelapp.backend.model.entities.RoomTypeDao;
import es.udc.hotelapp.backend.model.exceptions.InstanceNotFoundException;
import es.udc.hotelapp.backend.model.exceptions.RoomAlreadyExistsException;

@Service
@Transactional
public class RoomServiceImpl implements RoomService {

	@Autowired
	private RoomDao roomDao;
	@Autowired
	private RoomTypeDao typeDao;
	@Autowired
	private HotelDao hotelDao;

	@Override
	public Long addRoom(Room room) throws RoomAlreadyExistsException, InstanceNotFoundException {
		Optional<Room> roomfound = roomDao.findByNumber(room.getNumber());
		boolean type = typeDao.existsByName(room.getType().getName());
		boolean hotel = hotelDao.existsByName(room.getHotel().getName());
		if (roomfound.isPresent() && roomfound.get().getHotel() == room.getHotel()) {
			throw new RoomAlreadyExistsException(room.getId());
		}
		if (!hotel) {
			throw new InstanceNotFoundException("project.entities.hotel", room.getHotel().getId());
		}
		if (!type) {
			typeDao.save(room.getType());
		}
		roomDao.save(room);
		return room.getId();

	}

	@Override
	public void updateRoom(Room r1) {
		Optional<Room> roomFound = roomDao.findById(r1.getId());
		if( roomFound.isPresent()) {
			
			Room roomActual = roomFound.get();
			roomActual.setNumber(r1.getNumber());
			roomActual.setStatus(r1.getStatus());
			roomActual.setType(r1.getType());
			roomActual.setHotel(r1.getHotel());
			
			roomDao.save(roomActual);
		}
	}

	@Override
	public void removeRoom(Room r1) throws InstanceNotFoundException {
		Optional<Room> roomfound = roomDao.findById(r1.getId());
		if (roomfound.isPresent()) {
			roomDao.delete(r1);
		} else
			throw new InstanceNotFoundException("project.entities.room", r1.getId());

	}

	@Override
	public List<Room> findRooms(String status, Long hotelid) {
		List<Room> result = new ArrayList<>();
		Iterable<Room> roomsfound = roomDao.findAll();
		if (roomsfound != null) {
			Iterator<Room> iter = roomsfound.iterator();
			while (iter.hasNext()) {
				Room room = iter.next();
				if (room.getStatus().toString() == status && room.getHotel().getId() == hotelid)
					result.add(room);
			}
		}
		return result;

	}

	@Override
	@Transactional(readOnly = true)
	public Room findRoom(Long id) throws InstanceNotFoundException {
		Optional<Room> room = roomDao.findById(id);
		if(! room.isPresent()) {
			throw new InstanceNotFoundException("project.entities.room", id);
		}
		return room.get();
	}
}
