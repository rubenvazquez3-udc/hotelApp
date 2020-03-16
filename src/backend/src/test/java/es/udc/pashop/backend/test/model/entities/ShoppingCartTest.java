package es.udc.pashop.backend.test.model.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import es.udc.pashop.backend.model.entities.Category;
import es.udc.pashop.backend.model.exceptions.MaxItemsExceededException;
import es.udc.pashop.backend.model.exceptions.MaxQuantityExceededException;
import es.udc.pashop.backend.model.entities.Product;
import es.udc.pashop.backend.model.entities.ShoppingCart;
import es.udc.pashop.backend.model.entities.ShoppingCartItem;

public class ShoppingCartTest {
	
	private Product createProduct(String name, BigDecimal price) {
		return new Product(name, "description", price, new Category());
	}
	
	@Test
	public void testTotals() throws MaxItemsExceededException, MaxQuantityExceededException {
		
		BigDecimal price1 = new BigDecimal("10.5");
		Product product1 = createProduct("product1", price1);
		
		BigDecimal price2 = new BigDecimal("15.3");
		Product product2 = createProduct("product2", price2);
		
		ShoppingCart shoppingCart = new ShoppingCart();
		
		int quantity1 = 1;
		ShoppingCartItem shoppingCartItem1 = new ShoppingCartItem(product1, shoppingCart, quantity1);
		
		int quantity2 = 2;
		ShoppingCartItem shoppingCartItem2 = new ShoppingCartItem(product2, shoppingCart, quantity2);
		
		shoppingCart.addItem(shoppingCartItem1);
		shoppingCart.addItem(shoppingCartItem2);
		
		int totalQuantity = quantity1 + quantity2;
		BigDecimal totalPrice = price1.multiply(new BigDecimal(quantity1))
			.add(price2.multiply(new BigDecimal(quantity2)));
		
		assertEquals(totalQuantity, shoppingCart.getTotalQuantity());
		assertEquals(totalPrice, shoppingCart.getTotalPrice());
		
	}

}
