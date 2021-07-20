package es.udc.hotelapp.backend.rest.dtos;

import java.util.List;
import java.util.stream.Collectors;

import es.udc.hotelapp.backend.model.entities.Product;

public class ProductConversor {

	private ProductConversor() {
	}

	public final static Product toProduct(ProductDto productDto) {
		return new Product(productDto.getName(), productDto.getDescription(), productDto.getPrice(),
				HotelConversor.toHotel(productDto.getHotel()));
	}

	public final static ProductDto toProductDto(Product product) {
		return new ProductDto(product.getId(), product.getName(), product.getDescription(), product.getPrice(),
				HotelConversor.toHotelDto(product.getHotel()));
	}
	public final static List<ProductDto> toProductDtos(List<Product> products){
		return products.stream().map(p-> toProductDto(p)).collect(Collectors.toList());
	}
}
