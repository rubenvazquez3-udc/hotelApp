package es.udc.hotelapp.backend.model.exceptions;

@SuppressWarnings("serial")
public class ServiceAlreadyExistsException extends Exception {
	private String name;

	public String getName() {
		return name;
	}
	
	public ServiceAlreadyExistsException( String name) {
		super( "The service whith name: " + name + "already Exists");
		this.name = name;
	}

}