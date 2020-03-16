package es.udc.pashop.backend.model.services;

import es.udc.pashop.backend.model.exceptions.InstanceNotFoundException;
import es.udc.pashop.backend.model.entities.Order;
import es.udc.pashop.backend.model.entities.ShoppingCart;
import es.udc.pashop.backend.model.entities.User;
import es.udc.pashop.backend.model.exceptions.PermissionException;

public interface PermissionChecker {
	
	public void checkUserExists(Long userId) throws InstanceNotFoundException;
	
	public User checkUser(Long userId) throws InstanceNotFoundException;
	
	public ShoppingCart checkShoppingCartExistsAndBelongsTo(Long shoppingCartId, Long userId)
		throws PermissionException, InstanceNotFoundException;
	
	public Order checkOrderExistsAndBelongsTo(Long orderId, Long userId)
		throws PermissionException, InstanceNotFoundException;
	
}
