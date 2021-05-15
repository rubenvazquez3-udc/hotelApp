package es.udc.hotelapp.backend.model.services;

import java.time.LocalDate;
import java.util.List;

import es.udc.hotelapp.backend.model.entities.GuestReservation;
import es.udc.hotelapp.backend.model.entities.RoomReservation;
import es.udc.hotelapp.backend.model.entities.RoomTypeReservation;
import es.udc.hotelapp.backend.model.exceptions.IncorrectReservationException;
import es.udc.hotelapp.backend.model.exceptions.InstanceNotFoundException;

public interface ReservationService {
	
	public RoomTypeReservation addReservation(RoomTypeReservation rt1) throws InstanceNotFoundException;
	
	public List<RoomTypeReservation> findReservations(String username);
	
	public List<RoomTypeReservation> findReservationsHotel(Long id);
	
	public List<RoomTypeReservation> findReservationHotelDate(Long id, LocalDate date);
	
	public void updateReservation (RoomTypeReservation rt2);
	
	public RoomReservation assignReservation( RoomReservation rr1, Long id) throws IncorrectReservationException;
	
	public RoomTypeReservation findById(Long id) throws InstanceNotFoundException;
	
	public GuestReservation addGuest(GuestReservation gr1) throws IncorrectReservationException;
	
	public void updateGuest(GuestReservation gr1) throws InstanceNotFoundException;
	
	public GuestReservation findGuestReservationById(Long id) throws InstanceNotFoundException;

}
