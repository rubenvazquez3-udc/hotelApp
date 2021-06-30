package es.udc.hotelapp.backend.model.exceptions;


@SuppressWarnings("serial")
public class ProductAlreadyExistsException extends Exception{
	
	private String name;

	public String getName() {
		return name;
	}
	
	public ProductAlreadyExistsException( String name) {
		super( "The product whith name: " + name + "already Exists");
		this.name = name;
	}

}