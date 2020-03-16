package es.udc.pashop.backend.model.entities;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface OrderItemDao extends PagingAndSortingRepository<OrderItem, Long> {}
