package es.udc.hotelapp.backend.model.entities;

public class Guest {
	private Long id;
	private String name;
	private String surname;
	private String dni;
	private String address;

	public Guest() {
	}

	public Guest(String name, String surname, String dni, String address) {
		super();
		this.name = name;
		this.surname = surname;
		this.dni = dni;
		this.address = address;
	}

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
