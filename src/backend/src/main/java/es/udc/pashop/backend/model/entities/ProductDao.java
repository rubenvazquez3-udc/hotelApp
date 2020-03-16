package es.udc.pashop.backend.model.entities;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductDao extends PagingAndSortingRepository<Product, Long>, CustomizedProductDao {}
