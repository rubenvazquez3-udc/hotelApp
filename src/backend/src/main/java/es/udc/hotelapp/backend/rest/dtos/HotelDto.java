package es.udc.hotelapp.backend.rest.dtos;

public class HotelDto {

	private Long id;
	private String name;
	private String manager;
	private String address;

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

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public HotelDto(Long id, String name, String manager, String address) {
		super();
		this.id = id;
		this.name = name;
		this.manager = manager;
		this.address = address;
	}

	public HotelDto() {
	}

}
