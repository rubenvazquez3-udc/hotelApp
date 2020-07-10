package es.udc.hotelapp.backend.model.services;

import es.udc.hotelapp.backend.model.exceptions.InstanceNotFoundException;
import es.udc.hotelapp.backend.model.exceptions.PermissionException;
import es.udc.hotelapp.backend.model.entities.Account;
import es.udc.hotelapp.backend.model.entities.Reservation;
import es.udc.hotelapp.backend.model.entities.User;

public interface PermissionChecker {
	
	public void checkUserExists(Long userId) throws InstanceNotFoundException;
	
	public User checkUser(Long userId) throws InstanceNotFoundException;
	
	public Reservation checkIfReservationExistsAndBelongsTo(Long reservationId, Long userId) throws InstanceNotFoundException, PermissionException;
	
	
	public Account checkIfAccountExistsAndBelongsTo ( Long accountId, Long reservationId) throws InstanceNotFoundException, PermissionException;
}
