package es.udc.pashop.backend.test.model.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import es.udc.pashop.backend.model.entities.Category;
import es.udc.pashop.backend.model.entities.Order;
import es.udc.pashop.backend.model.entities.OrderItem;
import es.udc.pashop.backend.model.entities.Product;

public class OrderTest {
	
	private Product createProduct(String name, BigDecimal price) {
		return new Product(name, "description", price, new Category());
	}
	
	@Test
	public void testGetTotalPrice() {
		
		Product product1 = createProduct("product1", new BigDecimal("10.5"));		
		Product product2 = createProduct("product2",  new BigDecimal("15.3"));
		
		Order order = new Order();
		
		BigDecimal price1 = product1.getPrice().add(new BigDecimal(1));
		int quantity1 = 1;
		OrderItem item1 = new OrderItem(product1, price1, quantity1);
		
		BigDecimal price2 = product2.getPrice().add(new BigDecimal(1));
		int quantity2 = 2;
		OrderItem item2 = new OrderItem(product2, price2, quantity2);
		
		order.addItem(item1);
		order.addItem(item2);
		
		BigDecimal totalPrice = price1.multiply(new BigDecimal(quantity1))
			.add(price2.multiply(new BigDecimal(quantity2)));
		
		assertEquals(totalPrice, order.getTotalPrice());
		
	}

}
