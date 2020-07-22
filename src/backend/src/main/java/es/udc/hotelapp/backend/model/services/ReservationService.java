package es.udc.hotelapp.backend.model.services;

import java.util.List;

import es.udc.hotelapp.backend.model.entities.GuestReservation;
import es.udc.hotelapp.backend.model.entities.RoomReservation;
import es.udc.hotelapp.backend.model.entities.RoomTypeReservation;
import es.udc.hotelapp.backend.model.exceptions.IncorrectHotelException;
import es.udc.hotelapp.backend.model.exceptions.IncorrectReservationException;
import es.udc.hotelapp.backend.model.exceptions.InstanceNotFoundException;

public interface ReservationService {
	
	public Long addReservation(RoomTypeReservation rt1) throws InstanceNotFoundException;
	
	public List<RoomTypeReservation> findReservations(String username);
	
	public void updateReservation (RoomTypeReservation rt2) throws IncorrectHotelException;
	
	public Long assignReservation( RoomReservation rr1) throws InstanceNotFoundException;
	
	public RoomTypeReservation findById(Long id) throws InstanceNotFoundException;
	
	public Long addGuest(GuestReservation gr1) throws IncorrectReservationException;
	
	public GuestReservation findGuestReservationById(Long id) throws InstanceNotFoundException;

}
