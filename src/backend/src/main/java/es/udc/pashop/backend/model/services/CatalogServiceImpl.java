package es.udc.pashop.backend.model.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.pashop.backend.model.exceptions.InstanceNotFoundException;
import es.udc.pashop.backend.model.entities.Category;
import es.udc.pashop.backend.model.entities.CategoryDao;
import es.udc.pashop.backend.model.entities.Product;
import es.udc.pashop.backend.model.entities.ProductDao;

@Service
@Transactional(readOnly=true)
public class CatalogServiceImpl implements CatalogService {
	
	@Autowired
	private CategoryDao categoryDao;
	
	@Autowired
	private ProductDao productDao;

	@Override
	public List<Category> findAllCategories() {
		
		Iterable<Category> categories = categoryDao.findAll(Sort.by(Sort.Direction.ASC, "name"));
		List<Category> categoriesAsList = new ArrayList<>();
		
		categories.forEach(c -> categoriesAsList.add(c));
		
		return categoriesAsList;
	}

	@Override
	public Product findProductById(Long id) throws InstanceNotFoundException {
		
		Optional<Product> product = productDao.findById(id);
		
		if (!product.isPresent()) {
			throw new InstanceNotFoundException("project.entities.product", id);
		}
		
		return product.get();

	}

	@Override
	public Block<Product> findProducts(Long categoryId, String keywords, int page, int size) {
		
		Slice<Product> slice = productDao.find(categoryId, keywords, page, size);
		
		return new Block<>(slice.getContent(), slice.hasNext());
		
	}

}
