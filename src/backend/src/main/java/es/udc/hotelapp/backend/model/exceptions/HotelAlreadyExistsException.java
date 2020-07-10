package es.udc.hotelapp.backend.model.exceptions;

@SuppressWarnings("serial")
public class HotelAlreadyExistsException extends Exception {
	private String name;

	public String getName() {
		return name;
	}
	
	public HotelAlreadyExistsException( String name) {
		super( "The hotel whith name: " + name + "already Exists");
		this.name = name;
	}

}