package es.udc.hotelapp.backend.model.entities;

public class Address {

	private String state;
	private String road;
	private String city;
	private String country;

	public Address() {

	}

	public Address(String state, String road, String city, String country) {
		super();
		this.state = state;
		this.road = road;
		this.city = city;
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getRoad() {
		return road;
	}

	public void setRoad(String road) {
		this.road = road;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

}
