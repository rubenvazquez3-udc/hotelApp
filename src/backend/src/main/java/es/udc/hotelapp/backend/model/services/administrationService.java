package es.udc.hotelapp.backend.model.services;

import java.time.LocalDate;
import java.util.List;

import es.udc.hotelapp.backend.model.entities.Hotel;
import es.udc.hotelapp.backend.model.entities.Reservation;
import es.udc.hotelapp.backend.model.entities.Room;
import es.udc.hotelapp.backend.model.entities.RoomType;
import es.udc.hotelapp.backend.model.entities.RoomTypeReservation;
import es.udc.hotelapp.backend.model.exceptions.HotelAlreadyExistsException;
import es.udc.hotelapp.backend.model.exceptions.InstanceNotFoundException;


public interface administrationService {
	
	public Long addRoom(Long number, RoomType type, Hotel hotel);
	public void updateRoom(Room r1);
	public void removeRoom (Room r1) throws InstanceNotFoundException;
	public Room findRoom (Long userId, Long reservationId);
	public List<Room> findUsedRooms ();
	public List<Room> findAvailableRooms();
	public List<Reservation> findReservations (LocalDate inbound);
	public Reservation findReservation(RoomTypeReservation rtr1);
	public void updateReservation (Reservation r1);
	public Long createReservation ( Reservation reservation);
	public Long addReservation (Reservation r1, RoomType rt1, int quantity);
	public Long addRoomReservation (Reservation r1, Room r2);
	public Long createRoomType ( String name);
	
}
