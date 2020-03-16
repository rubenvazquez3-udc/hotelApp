package es.udc.pashop.backend.model.services;

import java.util.List;

import es.udc.pashop.backend.model.exceptions.InstanceNotFoundException;
import es.udc.pashop.backend.model.entities.Category;
import es.udc.pashop.backend.model.entities.Product;

public interface CatalogService {
	
	List<Category> findAllCategories();
	
	Product findProductById(Long id) throws InstanceNotFoundException;
	
	Block<Product> findProducts(Long categoryId, String keywords, int page, int size);

}
