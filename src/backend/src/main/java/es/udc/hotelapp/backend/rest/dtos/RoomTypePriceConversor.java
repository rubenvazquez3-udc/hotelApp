package es.udc.hotelapp.backend.rest.dtos;

import es.udc.hotelapp.backend.model.entities.RoomTypePrice;

public class RoomTypePriceConversor {
	private RoomTypePriceConversor() {
	}

	public final static RoomTypePriceDto toRoomTypePriceDto(RoomTypePrice price) {

		return new RoomTypePriceDto(price.getId(),
				RoomTypeConversor.toRoomTypeDto(price.getType()), price.getPrice());
	}

	public final static RoomTypePrice toRoomTypePrice(RoomTypePriceDto priceDto) {

		return new RoomTypePrice(HotelConversor.toHotel(priceDto.getHotel()),
				RoomTypeConversor.toRoomType(priceDto.getType()), priceDto.getPrice());
	}

}
