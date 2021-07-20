package es.udc.hotelapp.backend.model.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Hotel {
	private Long id;
	private String name;
	private String manager;
	private String address;
	private String phonenumber;
	private String description;
	
	private Set<RoomTypePrice> prices = new HashSet<>();
	
	private Set<Photo> photos = new HashSet<>();
	
	public Hotel() {}

	public Hotel(String name, String manager, String address, String phonenumber, String description) {
		this.name = name;
		this.manager = manager;
		this.address = address;
		this.phonenumber = phonenumber;
		this.description = description;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@OneToMany(mappedBy="hotel")
	public Set<RoomTypePrice> getPrices() {
		return prices;
	}

	public void setPrices(Set<RoomTypePrice> prices) {
		this.prices = prices;
	}

	@OneToMany(mappedBy="hotel")
	public Set<Photo> getPhotos() {
		return photos;
	}

	public void setPhotos(Set<Photo> photos) {
		this.photos = photos;
	}

	public void addPhoto(Photo item) {
		photos.add(item);
		item.setHotel(this);
	}
	
}
