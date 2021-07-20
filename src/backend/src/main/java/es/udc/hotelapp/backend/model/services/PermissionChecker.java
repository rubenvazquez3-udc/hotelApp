package es.udc.hotelapp.backend.model.services;

import es.udc.hotelapp.backend.model.exceptions.InstanceNotFoundException;

import es.udc.hotelapp.backend.model.entities.User;

public interface PermissionChecker {
	

	public User checkUser(Long userId) throws InstanceNotFoundException;
	
	public boolean checkIfPossibleToBook(Long hotelid, String in, String out, String type, int quantity);
	
}
