package es.udc.hotelapp.backend.test.model.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import es.udc.hotelapp.backend.model.exceptions.DuplicateInstanceException;
import es.udc.hotelapp.backend.model.exceptions.InstanceNotFoundException;
import es.udc.hotelapp.backend.model.entities.User;
import es.udc.hotelapp.backend.model.entities.User.RoleType;
import es.udc.hotelapp.backend.model.exceptions.IncorrectLoginException;
import es.udc.hotelapp.backend.model.exceptions.IncorrectPasswordException;
import es.udc.hotelapp.backend.model.services.UserService;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class UserServiceTest {
	
	private final Long NON_EXISTENT_ID = new Long(-1);
	
	@Autowired
	private UserService userService;
	
	private User createUser(String userName) {
		return new User(userName, "password", "firstName", "lastName", userName + "@" + userName + ".com", "C/ La Locura 20, España");
	}
	
	@Test
	public void testSignUpAndLoginFromId() throws DuplicateInstanceException, InstanceNotFoundException {
		
		User user = createUser("user");
		user.setRole(RoleType.USER);
		
		User manager = createUser("manager");
		manager.setRole(RoleType.MANAGER);
		
		User hotel = createUser("hotel");
		hotel.setRole(RoleType.HOTEL);
		
		User admin = createUser("admin");
		admin.setRole(RoleType.ADMIN);
		
		userService.signUp(user);
		
		userService.signUp(admin);
		userService.signUp(manager);
		userService.signUp(hotel);
		
		User loggedInUser = userService.loginFromId(user.getId());
		
		User loggedInAdmin = userService.loginFromId(admin.getId());
		
		User loggedInManager = userService.loginFromId(manager.getId());
		
		User loggedInHotel = userService.loginFromId(hotel.getId());
		
		assertEquals(user, loggedInUser);
		assertEquals(User.RoleType.USER, user.getRole());
		
		assertEquals(admin, loggedInAdmin);
		assertEquals(User.RoleType.ADMIN, admin.getRole());
		
		assertEquals(manager, loggedInManager);
		assertEquals(User.RoleType.MANAGER, manager.getRole());
		
		assertEquals(hotel, loggedInHotel);
		assertEquals(User.RoleType.HOTEL, hotel.getRole());
		
	}
	
	@Test
	public void testSignUpDuplicatedUserName() throws DuplicateInstanceException {
		
		User user = createUser("user");
		user.setRole(RoleType.USER);
		
		userService.signUp(user);
		assertThrows(DuplicateInstanceException.class, () -> userService.signUp(user));
		
	}
	
	@Test
	public void testLoginFromNonExistentId() {
		assertThrows(InstanceNotFoundException.class, () -> userService.loginFromId(NON_EXISTENT_ID));
	}
	
	@Test
	public void testLogin() throws DuplicateInstanceException, IncorrectLoginException {
		
		User user = createUser("user");
		user.setRole(RoleType.USER);
		String clearPassword = user.getPassword();
				
		userService.signUp(user);
		
		User loggedInUser = userService.login(user.getUserName(), clearPassword);
		
		assertEquals(user, loggedInUser);
		
	}
	
	@Test
	public void testLoginWithIncorrectPassword() throws DuplicateInstanceException {
		
		User user = createUser("user");
		user.setRole(RoleType.USER);
		String clearPassword = user.getPassword();
		
		userService.signUp(user);
		assertThrows(IncorrectLoginException.class, () -> userService.login(user.getUserName(), 'X' + clearPassword));
		
	}
	
	@Test
	public void testLoginWithNonExistentUserName() {
		assertThrows(IncorrectLoginException.class, () -> userService.login("X", "Y"));
	}
	
	@Test
	public void testUpdateProfile() throws InstanceNotFoundException, DuplicateInstanceException {
		
		User user = createUser("user");
		user.setRole(RoleType.USER);
		
		userService.signUp(user);
		
		user.setFirstName('X' + user.getFirstName());
		user.setLastName('X' + user.getLastName());
		user.setEmail('X' + user.getEmail());
		
		userService.updateProfile(user.getId(), 'X' + user.getFirstName(), 'X' + user.getLastName(),
			'X' + user.getEmail(),'X' + user.getAddress());
		
		User updatedUser = userService.loginFromId(user.getId());
		
		assertEquals(user, updatedUser);
		
	}
	
	@Test
	public void testUpdateProfileWithNonExistentId() {
		assertThrows(InstanceNotFoundException.class, () ->
			userService.updateProfile(NON_EXISTENT_ID, "X", "X", "X", "X"));
	}
	
	@Test
	public void testChangePassword() throws DuplicateInstanceException, InstanceNotFoundException,
		IncorrectPasswordException, IncorrectLoginException {
		
		User user = createUser("user");
		user.setRole(RoleType.USER);
		String oldPassword = user.getPassword();
		String newPassword = 'X' + oldPassword;
		
		userService.signUp(user);
		userService.changePassword(user.getId(), oldPassword, newPassword);
		userService.login(user.getUserName(), newPassword);
		
	}
	
	@Test
	public void testChangePasswordWithNonExistentId() {
		assertThrows(InstanceNotFoundException.class, () ->
			userService.changePassword(NON_EXISTENT_ID, "X", "Y"));
	}
	
	@Test
	public void testChangePasswordWithIncorrectPassword() throws DuplicateInstanceException {
		
		User user = createUser("user");
		user.setRole(RoleType.USER);
		String oldPassword = user.getPassword();
		String newPassword = 'X' + oldPassword;
		
		userService.signUp(user);
		assertThrows(IncorrectPasswordException.class, () ->
			userService.changePassword(user.getId(), 'Y' + oldPassword, newPassword));
		
	}
	
}
