package es.udc.hotelapp.backend.rest.dtos;

import es.udc.hotelapp.backend.model.entities.Photo;

public class PhotoConversor {
	
	private PhotoConversor() {}
	
	public final static PhotoDto toPhotoDto(Photo photo) {
		return new PhotoDto(photo.getId(),photo.getName());
	}

}
