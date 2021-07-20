package es.udc.hotelapp.backend.model.entities;

import org.springframework.data.domain.Slice;

public interface CustomizedServiceDao {
	Slice<Service> find( Long hotelid, int page, int size);

}
