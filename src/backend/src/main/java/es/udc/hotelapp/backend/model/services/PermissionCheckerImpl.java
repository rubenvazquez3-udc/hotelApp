package es.udc.hotelapp.backend.model.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.hotelapp.backend.model.exceptions.InstanceNotFoundException;
import es.udc.hotelapp.backend.model.exceptions.PermissionException;
import es.udc.hotelapp.backend.model.entities.Account;
import es.udc.hotelapp.backend.model.entities.AccountDao;
import es.udc.hotelapp.backend.model.entities.Reservation;
import es.udc.hotelapp.backend.model.entities.ReservationDao;
import es.udc.hotelapp.backend.model.entities.User;
import es.udc.hotelapp.backend.model.entities.UserDao;

@Service
@Transactional(readOnly=true)
public class PermissionCheckerImpl implements PermissionChecker {
	
	@Autowired
	private UserDao userDao;
	@Autowired
	private ReservationDao reservationDao;
	@Autowired
	private AccountDao accountDao;
	
	@Override
	public void checkUserExists(Long userId) throws InstanceNotFoundException {
		
		if (!userDao.existsById(userId)) {
			throw new InstanceNotFoundException("project.entities.user", userId);
		}
		
	}

	@Override
	public User checkUser(Long userId) throws InstanceNotFoundException {

		Optional<User> user = userDao.findById(userId);
		
		if (!user.isPresent()) {
			throw new InstanceNotFoundException("project.entities.user", userId);
		}
		
		return user.get();
		
	}

	@Override
	public Reservation checkIfReservationExistsAndBelongsTo(Long reservationId, Long userId)
			throws InstanceNotFoundException, PermissionException {
		Optional<Reservation> reservation = reservationDao.findById(reservationId);
		
		if(! reservation.isPresent()) {
			throw new InstanceNotFoundException("project.entities.reservation", reservationId);
		}
		if (! reservation.get().getUser().getId().equals(userId)) {
			throw new PermissionException();
		}
		return reservation.get();
	}

	@Override
	public Account checkIfAccountExistsAndBelongsTo(Long accountId, Long reservationId)
			throws InstanceNotFoundException, PermissionException {
		Optional<Account> account = accountDao.findById(accountId);
		if(! account.isPresent()) {
			throw new InstanceNotFoundException("project.entities.account", accountId);
		}
		
		if(! account.get().getReservation().getId().equals(reservationId)) {
			throw new PermissionException();
		}
		return account.get();
	}

}
