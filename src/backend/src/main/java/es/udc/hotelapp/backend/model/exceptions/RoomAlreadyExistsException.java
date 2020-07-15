package es.udc.hotelapp.backend.model.exceptions;
@SuppressWarnings("serial")
public class RoomAlreadyExistsException extends Exception{

	private Long id;

	public Long getId() {
		return id;
	}
	
	public RoomAlreadyExistsException( Long id) {
		super( "The room whith name: " + id + "already Exists");
		this.id = id;
	}

}