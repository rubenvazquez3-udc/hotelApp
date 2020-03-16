package es.udc.pashop.backend.rest.dtos;

import java.util.List;
import java.util.stream.Collectors;

import es.udc.pashop.backend.model.entities.Product;

public class ProductConversor {
	
	private ProductConversor() {}
	
	public final static ProductDto toProductDto(Product product) {
		
		return new ProductDto(product.getId(), product.getName(), product.getDescription(), product.getPrice(),
			product.getCategory().getId());

	}
	
	public final static List<ProductSummaryDto> toProductSummaryDtos(List<Product> products) {
		return products.stream().map(p -> toProductSummaryDto(p)).collect(Collectors.toList());
	}
	
	private final static ProductSummaryDto toProductSummaryDto(Product product) {
		return new ProductSummaryDto(product.getId(), product.getName(), product.getCategory().getId());
	}

}
