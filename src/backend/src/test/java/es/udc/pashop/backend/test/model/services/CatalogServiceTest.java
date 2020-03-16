package es.udc.pashop.backend.test.model.services;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import es.udc.pashop.backend.model.exceptions.InstanceNotFoundException;
import es.udc.pashop.backend.model.entities.Category;
import es.udc.pashop.backend.model.entities.CategoryDao;
import es.udc.pashop.backend.model.entities.Product;
import es.udc.pashop.backend.model.entities.ProductDao;
import es.udc.pashop.backend.model.services.Block;
import es.udc.pashop.backend.model.services.CatalogService;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class CatalogServiceTest {
	
	private final Long NON_EXISTENT_ID = new Long(-1);
	
	@Autowired
	private CategoryDao categoryDao;
	
	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private CatalogService catalogService;
	
	private Product createProduct(String name, Category category) {
		return new Product(name, "description", new BigDecimal(1), category);
	}
	
	@Test
	public void testFindAllCategories() {
		
		Category category1 = new Category("category1");
		Category category2 = new Category("category2");
		
		categoryDao.save(category2);
		categoryDao.save(category1);
				
		assertEquals(Arrays.asList(category1, category2), catalogService.findAllCategories());
		
	}
	
	@Test
	public void testFindProductById() throws InstanceNotFoundException {
		
		Category category = new Category("category");
		Product product = createProduct("product", category);
		
		categoryDao.save(category);
		productDao.save(product);
		
		assertEquals(product, catalogService.findProductById(product.getId()));
		
		
	}
	
	@Test
	public void testFindProductByNonExistentId() {
		assertThrows(InstanceNotFoundException.class, () -> catalogService.findProductById(NON_EXISTENT_ID));
	}
	
	
	@Test
	public void testFindProductsByKeywords() {
		
		Category category = new Category("category");
		Product product1 = createProduct("product 1", category);
		Product product2 = createProduct("X Product", category);
		Product product3 = createProduct("another", category);
		
		categoryDao.save(category);
		productDao.save(product1);
		productDao.save(product2);
		productDao.save(product3);
		
		Block<Product> expectedBlock = new Block<>(Arrays.asList(product1, product2), false);
		
		assertEquals(expectedBlock, catalogService.findProducts(null, "PrOd", 0, 2));
		
	}
	
	@Test
	public void testFindProductsByCategory() {
		
		Category category1 = new Category("category1");
		Category category2 = new Category("category2");
		Product product1 = createProduct("product1", category1);
		Product product2 = createProduct("product2", category2);
		
		categoryDao.save(category1);
		categoryDao.save(category2);
		productDao.save(product1);
		productDao.save(product2);
		
		Block<Product> expectedBlock = new Block<>(Arrays.asList(product1), false);
		
		assertEquals(expectedBlock, catalogService.findProducts(category1.getId(), null, 0, 1));
		
	}
	
	@Test
	public void testFindProductsByAllCriteria() {
		
		Category category1 = new Category("category 1");
		Product product1 = createProduct("product 1", category1);
		Product product2 = createProduct("another", category1);
		Category category2 = new Category("category 2");
		Product product3 = createProduct("product 3", category2);
		
		categoryDao.save(category1);
		productDao.save(product1);
		productDao.save(product2);
		categoryDao.save(category2);
		productDao.save(product3);
		
		Block<Product> expectedBlock = new Block<>(Arrays.asList(product1), false);
		
		assertEquals(expectedBlock, catalogService.findProducts(category1.getId(), "product", 0, 2));
		
	}
	
	@Test
	public void testFindAllProducts() {
		
		Category category1 = new Category("category 1");
		Product product1 = createProduct("product 1", category1);
		Category category2 = new Category("category 2");
		Product product2 = createProduct("product 2", category2);
		
		categoryDao.save(category1);
		productDao.save(product1);
		categoryDao.save(category2);
		productDao.save(product2);
		
		Block<Product> expectedBlock = new Block<>(Arrays.asList(product1, product2), false);
		
		assertEquals(expectedBlock, catalogService.findProducts(null, "", 0, 2));
		assertEquals(expectedBlock, catalogService.findProducts(null, null, 0, 2));
		
	}
	
	@Test
	public void testFindProductsOrder() {
		
		Category category = new Category("category");
		Product product1 = createProduct("product 1", category);
		Product product2 = createProduct("product 2", category);
		
		categoryDao.save(category);
		productDao.save(product2);
		productDao.save(product1);
		
		Block<Product> expectedBlock = new Block<>(Arrays.asList(product1, product2), false);
		
		assertEquals(expectedBlock, catalogService.findProducts(null, null, 0, 2));
		
	}
	
	@Test
	public void testFindNoProducts() {
		
		Category category = new Category("category");
		Product product = createProduct("product", category);
		
		categoryDao.save(category);
		productDao.save(product);
		
		Block<Product> expectedBlock = new Block<>(new ArrayList<>(), false);
		
		assertEquals(expectedBlock, catalogService.findProducts(null, "non-existent", 0, 1));
		
	}
	
	@Test
	public void testFindProductsByPages() {
		
		Category category = new Category("category");
		Product product1 = createProduct("product 1", category);
		Product product2 = createProduct("product 2", category);
		Product product3 = createProduct("product 3", category);
		
		categoryDao.save(category);
		productDao.save(product1);
		productDao.save(product2);
		productDao.save(product3);
		
		Block<Product> expectedBlock = new Block<>(Arrays.asList(product1, product2), true);
		assertEquals(expectedBlock, catalogService.findProducts(null, null, 0, 2));
		
		expectedBlock = new Block<>(Arrays.asList(product3), false);
		assertEquals(expectedBlock, catalogService.findProducts(null, null, 1, 2));		
		
		expectedBlock = new Block<>(new ArrayList<>(), false);
		assertEquals(expectedBlock, catalogService.findProducts(null, null, 2, 2));
		
	}
	
}





















