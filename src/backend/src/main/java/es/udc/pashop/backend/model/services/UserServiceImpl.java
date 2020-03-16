package es.udc.pashop.backend.model.services;

import java.util.Optional;

import es.udc.pashop.backend.model.exceptions.IncorrectLoginException;
import es.udc.pashop.backend.model.exceptions.IncorrectPasswordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.pashop.backend.model.exceptions.DuplicateInstanceException;
import es.udc.pashop.backend.model.exceptions.InstanceNotFoundException;
import es.udc.pashop.backend.model.entities.ShoppingCart;
import es.udc.pashop.backend.model.entities.ShoppingCartDao;
import es.udc.pashop.backend.model.entities.User;
import es.udc.pashop.backend.model.entities.UserDao;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	private PermissionChecker permissionChecker;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ShoppingCartDao shoppingCartDao;

	@Override
	public void signUp(User user) throws DuplicateInstanceException {
		
		if (userDao.existsByUserName(user.getUserName())) {
			throw new DuplicateInstanceException("project.entities.user", user.getUserName());
		}
		
		ShoppingCart shoppingCart = new ShoppingCart(user);
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRole(User.RoleType.USER);
		user.setShoppingCart(shoppingCart);
		
		userDao.save(user);
		shoppingCartDao.save(shoppingCart);
		
	}

	@Override
	@Transactional(readOnly=true)
	public User login(String userName, String password) throws IncorrectLoginException {
		
		Optional<User> user = userDao.findByUserName(userName);
		
		if (!user.isPresent()) {
			throw new IncorrectLoginException(userName, password);
		}
		
		if (!passwordEncoder.matches(password, user.get().getPassword())) {
			throw new IncorrectLoginException(userName, password);
		}
		
		return user.get();
		
	}
	
	@Override
	@Transactional(readOnly=true)
	public User loginFromId(Long id) throws InstanceNotFoundException {
		return permissionChecker.checkUser(id);
	}

	@Override
	public User updateProfile(Long id, String firstName, String lastName, String email) throws InstanceNotFoundException {
		
		User user = permissionChecker.checkUser(id);
		
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmail(email);
		
		return user;

	}

	@Override
	public void changePassword(Long id, String oldPassword, String newPassword)
		throws InstanceNotFoundException, IncorrectPasswordException {
		
		User user = permissionChecker.checkUser(id);
		
		if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
			throw new IncorrectPasswordException();
		} else {
			user.setPassword(passwordEncoder.encode(newPassword));
		}
		
	}

}
