package es.udc.hotelapp.backend.rest.dtos;

import java.util.List;
import java.util.stream.Collectors;

import es.udc.hotelapp.backend.model.entities.Service;

public class ServiceConversor {

	private ServiceConversor() {
	}

	public final static Service toService(ServiceDto serviceDto) {
		return new Service(serviceDto.getName(), serviceDto.getDescription(), serviceDto.getPrice(),
				HotelConversor.toHotel(serviceDto.getHotel()));
	}

	public final static ServiceDto toServiceDto(Service service) {
		return new ServiceDto(service.getId(), service.getName(), service.getDescription(), service.getPrice(),
				HotelConversor.toHotelDto(service.getHotel()));
	}
	public final static List<ServiceDto> toServiceDtos(List<Service> services){
		return services.stream().map(p-> toServiceDto(p)).collect(Collectors.toList());
	}
}
