package es.udc.hotelapp.backend.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import es.udc.hotelapp.backend.model.entities.GuestReservation;
import es.udc.hotelapp.backend.model.entities.Hotel;
import es.udc.hotelapp.backend.model.entities.Product;
import es.udc.hotelapp.backend.model.entities.Room;
import es.udc.hotelapp.backend.model.entities.RoomTypePrice;
import es.udc.hotelapp.backend.model.entities.Service;
import es.udc.hotelapp.backend.model.exceptions.HotelAlreadyExistsException;
import es.udc.hotelapp.backend.model.exceptions.InstanceNotFoundException;
import es.udc.hotelapp.backend.model.exceptions.ProductAlreadyExistsException;
import es.udc.hotelapp.backend.model.exceptions.RoomAlreadyExistsException;
import es.udc.hotelapp.backend.model.exceptions.ServiceAlreadyExistsException;
import es.udc.hotelapp.backend.model.services.Block;
import es.udc.hotelapp.backend.model.services.HotelService;
import es.udc.hotelapp.backend.model.services.ReservationService;
import es.udc.hotelapp.backend.model.services.RoomService;
import es.udc.hotelapp.backend.rest.common.ErrorsDto;
import es.udc.hotelapp.backend.rest.dtos.BlockDto;
import es.udc.hotelapp.backend.rest.dtos.GuestReservationDto;
import es.udc.hotelapp.backend.rest.dtos.HotelDto;
import es.udc.hotelapp.backend.rest.dtos.ProductDto;
import es.udc.hotelapp.backend.rest.dtos.RoomDto;
import es.udc.hotelapp.backend.rest.dtos.RoomTypeDto;
import es.udc.hotelapp.backend.rest.dtos.RoomTypePriceDto;
import es.udc.hotelapp.backend.rest.dtos.ServiceDto;
import static es.udc.hotelapp.backend.rest.dtos.HotelConversor.*;
import static es.udc.hotelapp.backend.rest.dtos.ServiceConversor.*;
import static es.udc.hotelapp.backend.rest.dtos.ProductConversor.*;
import static es.udc.hotelapp.backend.rest.dtos.RoomConversor.*;
import static es.udc.hotelapp.backend.rest.dtos.RoomTypeConversor.*;
import static es.udc.hotelapp.backend.rest.dtos.GuestReservationConversor.*;
import static es.udc.hotelapp.backend.rest.dtos.RoomTypePriceConversor.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/")
public class HotelController {

	private final static String HOTEL_ALREADY_EXISTS_EXCEPTION_CODE = "project.exceptions.HotelAlreadyExistsException";

	private final static String SERVICE_ALREADY_EXISTS_EXCEPTION_CODE = "project.exceptions.ServiceAlreadyExistsException";
	
	private final static String PRODUCT_ALREADY_EXISTS_EXCEPTION_CODE = "project.exceptions.ProductAlreadyExistsException";

	private final static String ROOM_ALREADY_EXISTS_EXCEPTION_CODE = "project.exceptions.RoomAlreadyExistsException";
	
	@Autowired
	private HotelService hotelService;
	@Autowired
	private RoomService roomService;
	
	@Autowired
	private ReservationService reservationService;

	@Autowired
	private MessageSource messageSource;

	@ExceptionHandler(HotelAlreadyExistsException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	@ResponseBody
	public ErrorsDto handleHotelAlreadyExistsException(HotelAlreadyExistsException exception, Locale locale) {
		String errorMessage = messageSource.getMessage(HOTEL_ALREADY_EXISTS_EXCEPTION_CODE, null,
				HOTEL_ALREADY_EXISTS_EXCEPTION_CODE, locale);
		return new ErrorsDto(errorMessage);

	}

	@ExceptionHandler(ServiceAlreadyExistsException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	@ResponseBody
	public ErrorsDto handleServiceAlreadyExistsException(ServiceAlreadyExistsException exception, Locale locale) {
		String errorMessage = messageSource.getMessage(SERVICE_ALREADY_EXISTS_EXCEPTION_CODE, null,
				SERVICE_ALREADY_EXISTS_EXCEPTION_CODE, locale);
		return new ErrorsDto(errorMessage);

	}
	
	@ExceptionHandler(ProductAlreadyExistsException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	@ResponseBody
	public ErrorsDto handleProductAlreadyExistsException(ServiceAlreadyExistsException exception, Locale locale) {
		String errorMessage = messageSource.getMessage(PRODUCT_ALREADY_EXISTS_EXCEPTION_CODE, null,
				PRODUCT_ALREADY_EXISTS_EXCEPTION_CODE, locale);
		return new ErrorsDto(errorMessage);

	}

	@ExceptionHandler(RoomAlreadyExistsException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	@ResponseBody
	public ErrorsDto handleRoomAlreadyExistsException(RoomAlreadyExistsException exception, Locale locale) {
		String errorMessage = messageSource.getMessage(ROOM_ALREADY_EXISTS_EXCEPTION_CODE, null,
				ROOM_ALREADY_EXISTS_EXCEPTION_CODE, locale);
		return new ErrorsDto(errorMessage);

	}
	
	/******************************************** HOTELS *****************************************/
		
	@PostMapping("/hotels")
	public HotelDto addHotel(@RequestBody HotelDto hotelDto) throws HotelAlreadyExistsException {
		Long id = null;
		Hotel hotel = toHotel(hotelDto);
		id = hotelService.createHotel(hotel);

		hotelDto.setId(id);
		return hotelDto;
	}

	@GetMapping("/hotels/{hotelid}")
	public HotelDto findHotel(@PathVariable Long hotelid) throws InstanceNotFoundException {

		return toHotelDto(hotelService.findById(hotelid));
	}

	@PutMapping("/hotels/{hotelid}")
	public HotelDto updateHotel(@PathVariable Long hotelid, @RequestBody HotelDto hotelDto)
			throws InstanceNotFoundException {
		Hotel hotel = toHotel(hotelDto);
		Long id = hotelDto.getId();
		hotel.setId(id);

		hotelService.updateHotel(hotel);

		return toHotelDto(hotel);
	}

	@GetMapping("/hotels")
	public List<HotelDto> findAllHotels() {
		List<HotelDto> list = new ArrayList<>();
		for (Hotel h : hotelService.findHotels()) {
			list.add(toHotelDto(h));
		}
		return list;
	}
	
	@DeleteMapping("/hotels/{hotelid}")
	public ResponseEntity<?> removeHotel(@PathVariable Long hotelid) throws InstanceNotFoundException {
		
		hotelService.removeHotel(hotelid);
		
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping("/hotels/{hotelid}/upload-photo")
	public ResponseEntity<?> uploadPhoto( @PathVariable Long hotelid, @RequestParam MultipartFile file){

	    if( file.isEmpty()){
	        return (ResponseEntity<?>) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	    boolean f = hotelService.uploadPhoto(file, hotelid);

	    if(f){
	        return (ResponseEntity<?>) ResponseEntity.status(HttpStatus.NO_CONTENT);
	    }

	    return (ResponseEntity<?>) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
	}

		/******************************************** ROOMS *****************************************/
	
	@PostMapping("/hotels/{hotelid}/rooms")
	public RoomDto addRoom(@PathVariable Long hotelid, @RequestBody RoomDto roomDto)
			throws InstanceNotFoundException, RoomAlreadyExistsException {
		Room r1 = toRoom(roomDto);
		r1.getHotel().setId(hotelid);
		roomService.addRoom(r1);
		return toRoomDto(r1);
	}

	@GetMapping("/hotels/{hotelid}/rooms/{id}")
	public RoomDto findRoom(@PathVariable Long hotelid, @PathVariable Long id) throws InstanceNotFoundException {
		return toRoomDto(roomService.findRoom(id));
	}

	@GetMapping("/hotels/{hotelid}/rooms")
	public BlockDto <RoomDto> findRooms(@PathVariable Long hotelid, @RequestParam(defaultValue= "") String status, @RequestParam(defaultValue = "") String type,
			@RequestParam(defaultValue = "0") int page) {
		Block<Room> rooms = roomService.findRooms(status, hotelid, type, page, 10);
		return new BlockDto<>(toRoomDtos(rooms.getItems()),rooms.getExistMoreItems());
	}

	@PutMapping("/hotels/{hotelid}/rooms/{roomid}")
	public RoomDto updateRoom(@PathVariable Long hotelid, @PathVariable Long roomid,
			@RequestBody RoomDto roomDto) {
		Room r = toRoom(roomDto);
		r.getHotel().setId(hotelid);
		r.getType().setId(roomDto.getType().getId());
		r.setId(roomid);
		roomService.updateRoom(r);

		return toRoomDto(r);
	}

	@DeleteMapping("/hotels/{hotelid}/rooms/{id}")
	public ResponseEntity<?> removeRoom(@PathVariable Long hotelid, @PathVariable Long id) throws InstanceNotFoundException {
		Room r1 = roomService.findRoom(id);
		roomService.removeRoom(r1);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/hotels/roomtypes")
	public List<RoomTypeDto> findAllRoomTypes() {
		return toRoomTypeDtos(roomService.findAllRoomTypes());
	}
	
	/******************************************** HOTEL GUESTS *****************************************/
	
	@GetMapping("/hotels/{hotelid}/guests")
	public BlockDto<GuestReservationDto> findGuests (@PathVariable Long hotelid,
			 @RequestParam( defaultValue = "") String username, @RequestParam(defaultValue = "0") int page){

		Block<GuestReservation> list = reservationService.findAllGuestReservation(hotelid, username, page, 10);
					
		return new BlockDto<>(toGuestReservationDtos(list.getItems()),list.getExistMoreItems());
	}
	
	/******************************************** HOTEL PRICES *****************************************/
	@PostMapping("/hotels/{hotelid}/prices")
	public RoomTypePriceDto addRoomTypePrice ( @PathVariable Long hotelid, @RequestBody RoomTypePriceDto priceDto) {
		
		RoomTypePrice price = toRoomTypePrice(priceDto);
		price.getHotel().setId(hotelid);
		price.getType().setId(priceDto.getType().getId());
		
		hotelService.addPrice(price);
		
		return toRoomTypePriceDto(price);
	}
	
	@PutMapping("/hotels/{hotelid}/prices/{id}")
	public RoomTypePriceDto updateRoomTypePrice( @PathVariable Long hotelid, @PathVariable Long id, @RequestBody RoomTypePriceDto pricedto) throws InstanceNotFoundException {
		RoomTypePrice price = toRoomTypePrice(pricedto);
		
		price.getHotel().setId(hotelid);
		price.getType().setId(pricedto.getType().getId());
		price.setId(id);
		
		hotelService.updateRoomTypePrice(price);
		
		return pricedto;
	}
		
	/******************************************** PRODUCTS & SERVICES *****************************************/
	
	

	@PostMapping("/hotels/{hotelid}/products")
	public ProductDto addProduct(@PathVariable Long hotelid, @RequestBody ProductDto productDto)
			throws InstanceNotFoundException, ProductAlreadyExistsException {
		Product p = toProduct(productDto);
		p.getHotel().setId(hotelid);
		Long id = hotelService.addProduct(p);
		p.setId(id);

		return toProductDto(p);
	}
	
	@PostMapping("/hotels/{hotelid}/services")
	public ServiceDto addService(@PathVariable Long hotelid, @RequestBody ServiceDto serviceDto)
			throws InstanceNotFoundException, ServiceAlreadyExistsException {
		Service s = toService(serviceDto);
		s.getHotel().setId(hotelid);
		Long id = hotelService.addService(s);
		s.setId(id);

		return toServiceDto(s);
	}

	@PutMapping("/hotels/{hotelid}/products/{id}")
	public ProductDto updateProduct(@PathVariable Long hotelid,@PathVariable Long id,  @RequestBody ProductDto pDto)
			throws InstanceNotFoundException {
		Product p = toProduct(pDto);
		p.setId(id);
		p.getHotel().setId(pDto.getHotel().getId());

		hotelService.updateProduct(p);

		return toProductDto(p);
	}

	@PutMapping("/hotels/{hotelid}/services/{id}")
	public ServiceDto updateService(@PathVariable Long hotelid,@PathVariable Long id,  @RequestBody ServiceDto serviceDto)
			throws InstanceNotFoundException {
		Service service = toService(serviceDto);
		service.setId(id);
		service.getHotel().setId(serviceDto.getHotel().getId());

		hotelService.updateService(service);

		return toServiceDto(service);
	}
	
	@GetMapping("/hotels/{hotelid}/products")
	public BlockDto<ProductDto> findAllProducts(@PathVariable Long hotelid, @RequestParam(defaultValue = "0") int page) throws InstanceNotFoundException {
		
		Block<Product> products = hotelService.findProducts(hotelid,page, 5);

		return new BlockDto<>(toProductDtos(products.getItems()),products.getExistMoreItems());

	}
	@GetMapping("/hotels/{hotelid}/services")
	public BlockDto<ServiceDto> findAllServices(@PathVariable Long hotelid, @RequestParam(defaultValue = "0") int page) {

		Block<Service> services = hotelService.findServices(hotelid,page, 5);

		return new BlockDto<>(toServiceDtos(services.getItems()),services.getExistMoreItems());

	}

	
	@GetMapping("/hotels/{hotelid}/services/{id}")
	public ServiceDto findService(@PathVariable Long hotelid, @PathVariable Long id) throws InstanceNotFoundException {

		return toServiceDto(hotelService.findService(id));

	}
	
	@GetMapping("/hotels/{hotelid}/products/{id}")
	public ProductDto findProduct(@PathVariable Long hotelid, @PathVariable Long id) throws InstanceNotFoundException {

		return toProductDto(hotelService.findProduct(id));

	}
	
	@DeleteMapping("/hotels/{hotelid}/services/{id}")
	public ResponseEntity<?> removeService(@PathVariable Long hotelid, @PathVariable Long id) throws InstanceNotFoundException {
		
		hotelService.removeService(id);
		
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/hotels/{hotelid}/products/{id}")
	public ResponseEntity<?> removeProduct(@PathVariable Long hotelid, @PathVariable Long id) throws InstanceNotFoundException {
		
		hotelService.removeProduct(id);
		
		return ResponseEntity.noContent().build();
	}

	

}

