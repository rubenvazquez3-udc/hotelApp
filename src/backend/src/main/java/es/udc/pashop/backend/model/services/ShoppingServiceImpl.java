package es.udc.pashop.backend.model.services;

import java.time.LocalDateTime;
import java.util.Optional;

import es.udc.pashop.backend.model.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.pashop.backend.model.entities.Order;
import es.udc.pashop.backend.model.entities.OrderDao;
import es.udc.pashop.backend.model.entities.OrderItem;
import es.udc.pashop.backend.model.entities.OrderItemDao;
import es.udc.pashop.backend.model.entities.Product;
import es.udc.pashop.backend.model.entities.ProductDao;
import es.udc.pashop.backend.model.entities.ShoppingCart;
import es.udc.pashop.backend.model.entities.ShoppingCartItem;
import es.udc.pashop.backend.model.entities.ShoppingCartItemDao;

@Service
@Transactional
public class ShoppingServiceImpl implements ShoppingService {
	
	@Autowired
	private PermissionChecker permissionChecker;
	
	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private ShoppingCartItemDao shoppingCartItemDao;
	
	@Autowired
	private OrderItemDao orderItemDao;
	
	@Autowired
	private OrderDao orderDao;

	@Override
	public ShoppingCart addToShoppingCart(Long userId, Long shoppingCartId, Long productId, int quantity)
		throws InstanceNotFoundException, PermissionException, MaxQuantityExceededException, MaxItemsExceededException {
		
		ShoppingCart shoppingCart = permissionChecker.checkShoppingCartExistsAndBelongsTo(shoppingCartId, userId);
		Optional<Product> product = productDao.findById(productId);
		
		if (!product.isPresent()) {
			throw new InstanceNotFoundException("project.entities.product", productId);
		}
		
		Optional<ShoppingCartItem> existingCartItem = shoppingCart.getItem(productId);
		
		if (existingCartItem.isPresent()) {
			existingCartItem.get().incrementQuantity(quantity);
		} else {
			ShoppingCartItem newCartItem = new ShoppingCartItem(product.get(), shoppingCart, quantity);
			shoppingCart.addItem(newCartItem);
			shoppingCartItemDao.save(newCartItem);
		}
		
		return shoppingCart;
		
	}
	
	@Override
	public ShoppingCart updateShoppingCartItemQuantity(Long userId, Long shoppingCartId, Long productId, int quantity)
		throws InstanceNotFoundException, PermissionException, MaxQuantityExceededException {
		
		ShoppingCart shoppingCart = permissionChecker.checkShoppingCartExistsAndBelongsTo(shoppingCartId, userId);
		Optional<ShoppingCartItem> existingCartItem = shoppingCart.getItem(productId);
		
		if (!existingCartItem.isPresent()) {
			throw new InstanceNotFoundException("project.entities.product", productId);
		}
		
		existingCartItem.get().setQuantity(quantity);
		
		return shoppingCart;
		
	}
	
	@Override
	public ShoppingCart removeShoppingCartItem(Long userId, Long shoppingCartId, Long productId)
		throws InstanceNotFoundException, PermissionException {
		
		ShoppingCart shoppingCart = permissionChecker.checkShoppingCartExistsAndBelongsTo(shoppingCartId, userId);
		Optional<ShoppingCartItem> existingCartItem = shoppingCart.getItem(productId);
		
		if (!existingCartItem.isPresent()) {
			throw new InstanceNotFoundException("project.entities.product", productId);
		}
		
		shoppingCart.removeItem(existingCartItem.get());
		shoppingCartItemDao.delete(existingCartItem.get());
		
		return shoppingCart;
		
	}
	
	@Override
	public Order buy(Long userId, Long shoppingCartId, String postalAddress, String postalCode)
			throws InstanceNotFoundException, PermissionException, EmptyShoppingCartException {
		
		ShoppingCart shoppingCart = permissionChecker.checkShoppingCartExistsAndBelongsTo(shoppingCartId, userId);
		
		if (shoppingCart.isEmpty()) {
			throw new EmptyShoppingCartException();
		}
		
		Order order = new Order(shoppingCart.getUser(), LocalDateTime.now(), postalAddress, postalCode);
		
		orderDao.save(order);
		
		for (ShoppingCartItem shoppingCartItem : shoppingCart.getItems()) {
			
			OrderItem orderItem = new OrderItem(shoppingCartItem.getProduct(),
				shoppingCartItem.getProduct().getPrice(), shoppingCartItem.getQuantity());
			
			order.addItem(orderItem);
			orderItemDao.save(orderItem);
			shoppingCartItemDao.delete(shoppingCartItem);
			
		}
		
		shoppingCart.removeAll();

		return order;

	}

	@Override
	@Transactional(readOnly=true)
	public Order findOrder(Long userId, Long orderId) throws InstanceNotFoundException, PermissionException {
		return permissionChecker.checkOrderExistsAndBelongsTo(orderId, userId);
	}

	@Override
	@Transactional(readOnly=true)
	public Block<Order> findOrders(Long userId, int page, int size) {
		
		Slice<Order> slice = orderDao.findByUserIdOrderByDateDesc(userId, PageRequest.of(page, size));
		
		return new Block<>(slice.getContent(), slice.hasNext());
		
	}

}
