package es.udc.pashop.backend.model.services;

import es.udc.pashop.backend.model.exceptions.*;
import es.udc.pashop.backend.model.entities.Order;
import es.udc.pashop.backend.model.entities.ShoppingCart;

public interface ShoppingService {
	
	ShoppingCart addToShoppingCart(Long userId, Long shoppingCartId, Long productId, int quantity) 
		throws InstanceNotFoundException, PermissionException, MaxQuantityExceededException, MaxItemsExceededException;
	
	ShoppingCart updateShoppingCartItemQuantity(Long userId, Long shoppingCartId, Long productId, int quantity)
		throws InstanceNotFoundException, PermissionException, MaxQuantityExceededException;
	
	ShoppingCart removeShoppingCartItem(Long userId, Long shoppingCartId, Long productId)
		throws InstanceNotFoundException, PermissionException;
	
	Order buy(Long userId, Long shoppingCartId, String postalAddress, String postalCode)
		throws InstanceNotFoundException, PermissionException, EmptyShoppingCartException;
	
	Order findOrder(Long userId, Long orderId) throws InstanceNotFoundException, PermissionException;
	
	Block<Order> findOrders(Long userId, int page, int size);

}
