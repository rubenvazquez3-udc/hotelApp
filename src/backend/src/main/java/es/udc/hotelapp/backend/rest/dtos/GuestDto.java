package es.udc.hotelapp.backend.rest.dtos;

public class GuestDto {
	private Long id;
	private String name;
	private String surname;
	private String dni;
	private String address;
	
	public GuestDto(Long id, String name, String surname, String dni, String address) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.dni = dni;
		this.address = address;
	}

	public GuestDto() {	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	
}
