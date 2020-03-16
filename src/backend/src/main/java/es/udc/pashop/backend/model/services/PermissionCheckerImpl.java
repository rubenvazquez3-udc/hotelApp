package es.udc.pashop.backend.model.services;

import java.util.Optional;

import es.udc.pashop.backend.model.exceptions.PermissionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.pashop.backend.model.exceptions.InstanceNotFoundException;
import es.udc.pashop.backend.model.entities.Order;
import es.udc.pashop.backend.model.entities.OrderDao;
import es.udc.pashop.backend.model.entities.ShoppingCart;
import es.udc.pashop.backend.model.entities.ShoppingCartDao;
import es.udc.pashop.backend.model.entities.User;
import es.udc.pashop.backend.model.entities.UserDao;

@Service
@Transactional(readOnly=true)
public class PermissionCheckerImpl implements PermissionChecker {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ShoppingCartDao shoppingCartDao;
	
	@Autowired
	private OrderDao orderDao;

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
	public ShoppingCart checkShoppingCartExistsAndBelongsTo(Long shoppingCartId, Long userId)
		throws PermissionException, InstanceNotFoundException {
		
		Optional<ShoppingCart> shoppingCart = shoppingCartDao.findById(shoppingCartId);
		
		if (!shoppingCart.isPresent()) {
			throw new InstanceNotFoundException("project.entities.shoppingCart", shoppingCartId);
		}
		
		if (!shoppingCart.get().getUser().getId().equals(userId)) {
			throw new PermissionException();
		}
		
		return shoppingCart.get();
		
	}

	@Override
	public Order checkOrderExistsAndBelongsTo(Long orderId, Long userId)
		throws PermissionException, InstanceNotFoundException {
		
		Optional<Order> order = orderDao.findById(orderId);
		
		if (!order.isPresent()) {
			throw new InstanceNotFoundException("project.entities.order", orderId);
		}
		
		if (!order.get().getUser().getId().equals(userId)) {
			throw new PermissionException();
		}
		
		return order.get();
		
	}

}
