package es.udc.hotelapp.backend.model.entities;

import org.springframework.data.domain.Slice;

public interface CustomizedHotelDao {
    Slice<Hotel> find( String name,String address, int page, int size);

}
