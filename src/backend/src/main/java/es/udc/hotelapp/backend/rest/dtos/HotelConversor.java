package es.udc.hotelapp.backend.rest.dtos;

import es.udc.hotelapp.backend.model.entities.Hotel;

public class HotelConversor {
	
	private HotelConversor() {}
	
	public final static Hotel toHotel(HotelDto hotelDto) {
		return new Hotel(hotelDto.getName(), hotelDto.getManager(),hotelDto.getAddress(),hotelDto.getPhoneNumber(), hotelDto.getDescription());
	}

	public final static HotelDto toHotelDto(Hotel hotel) {
		return new HotelDto(hotel.getId(),hotel.getName(),hotel.getManager(), hotel.getAddress(), hotel.getPhonenumber(), hotel.getDescription());
	}
}
