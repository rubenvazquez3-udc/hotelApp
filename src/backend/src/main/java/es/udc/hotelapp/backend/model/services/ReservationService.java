package es.udc.hotelapp.backend.model.services;

import java.util.List;

import es.udc.hotelapp.backend.model.entities.Account;
import es.udc.hotelapp.backend.model.entities.GuestReservation;
import es.udc.hotelapp.backend.model.entities.RoomReservation;
import es.udc.hotelapp.backend.model.entities.RoomTypeReservation;
import es.udc.hotelapp.backend.model.exceptions.IncorrectReservationException;
import es.udc.hotelapp.backend.model.exceptions.InstanceNotFoundException;
import es.udc.hotelapp.backend.model.exceptions.PermissionException;

public interface ReservationService {

	public RoomTypeReservation addReservation(RoomTypeReservation rt1) throws InstanceNotFoundException, PermissionException;

	public List<RoomTypeReservation> findReservations(Long id, String date, String username);
	
	public List<RoomTypeReservation> findReservations( Long hotelid, Long userid, String date);

	public void updateReservation(RoomTypeReservation rt2);

	public RoomReservation assignReservation(RoomReservation rr1, Long id) throws IncorrectReservationException;

	public RoomTypeReservation findById(Long id) throws InstanceNotFoundException;

	public GuestReservation addGuest(GuestReservation gr1) throws IncorrectReservationException;

	public GuestReservation findGuestReservationById(Long id) throws InstanceNotFoundException;

	public Block<GuestReservation> findAllGuestReservation(Long hotelid, String username,int page, int size);

	public void removeReservation(Long reservationid) throws InstanceNotFoundException;

	public Account createAccount(Account acc) throws IncorrectReservationException;

	public Account findAccount(Long reservationid);

	public Account addToAccount(Long serviceid, Long productid, Long reservationid, int quantity)
			throws InstanceNotFoundException;

}
