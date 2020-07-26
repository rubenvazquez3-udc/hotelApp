package es.udc.hotelapp.backend.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import es.udc.hotelapp.backend.model.entities.Hotel;
import es.udc.hotelapp.backend.model.entities.Room;
import es.udc.hotelapp.backend.model.entities.RoomTypeReservation;
import es.udc.hotelapp.backend.model.entities.Service;
import es.udc.hotelapp.backend.model.exceptions.HotelAlreadyExistsException;
import es.udc.hotelapp.backend.model.exceptions.IncorrectHotelException;
import es.udc.hotelapp.backend.model.exceptions.InstanceNotFoundException;
import es.udc.hotelapp.backend.model.exceptions.RoomAlreadyExistsException;
import es.udc.hotelapp.backend.model.exceptions.ServiceAlreadyExistsException;
import es.udc.hotelapp.backend.model.services.HotelService;
import es.udc.hotelapp.backend.model.services.ReservationService;
import es.udc.hotelapp.backend.model.services.RoomService;
import es.udc.hotelapp.backend.rest.common.ErrorsDto;
import es.udc.hotelapp.backend.rest.dtos.BlockDto;
import es.udc.hotelapp.backend.rest.dtos.HotelDto;
import es.udc.hotelapp.backend.rest.dtos.RoomDto;
import es.udc.hotelapp.backend.rest.dtos.RoomTypeReservationDto;
import es.udc.hotelapp.backend.rest.dtos.ServiceDto;
import es.udc.hotelapp.backend.rest.dtos.StatusConversor;

import static es.udc.hotelapp.backend.rest.dtos.HotelConversor.*;
import static es.udc.hotelapp.backend.rest.dtos.ServiceConversor.*;
import static es.udc.hotelapp.backend.rest.dtos.RoomConversor.*;
import static es.udc.hotelapp.backend.rest.dtos.RoomTypeReservationConversor.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/")
public class HotelController {

	private final static String HOTEL_ALREADY_EXISTS_EXCEPTION_CODE = "project.exceptions.HotelAlreadyExistsException";

	private final static String SERVICE_ALREADY_EXISTS_EXCEPTION_CODE = "project.exceptions.ServiceAlreadyExistsException";

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

	@ExceptionHandler(RoomAlreadyExistsException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	@ResponseBody
	public ErrorsDto handleRoomAlreadyExistsException(RoomAlreadyExistsException exception, Locale locale) {
		String errorMessage = messageSource.getMessage(ROOM_ALREADY_EXISTS_EXCEPTION_CODE, null,
				ROOM_ALREADY_EXISTS_EXCEPTION_CODE, locale);
		return new ErrorsDto(errorMessage);

	}

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
	public ResponseEntity updateHotel(@PathVariable Long hotelid, @RequestBody HotelDto hotelDto)
			throws InstanceNotFoundException {
		Hotel hotel = toHotel(hotelDto);
		Long id = hotelDto.getId();
		hotel.setId(id);

		hotelService.updateHotel(hotel);

		return ResponseEntity.noContent().build();
	}

	@GetMapping("/hotels")
	public List<HotelDto> findAllHotels() {
		List<HotelDto> list = new ArrayList<>();
		for (Hotel h : hotelService.findHotels()) {
			list.add(toHotelDto(h));
		}
		return list;
	}

	@GetMapping("/hotels/{hotelid}/services")
	public BlockDto<ServiceDto> findAllServices(@PathVariable Long hotelid) {

		return new BlockDto<>(toServiceDtos(hotelService.findServices(hotelid).getItems()),
				hotelService.findServices(hotelid).getExistMoreItems());

	}

	@PostMapping("/hotels/{hotelid}/services")
	public ServiceDto addService(@PathVariable Long hotelid, @RequestBody ServiceDto serviceDto)
			throws InstanceNotFoundException, ServiceAlreadyExistsException {
		Service s = toService(serviceDto);
		s.getHotel().setId(hotelid);
		Long id = hotelService.addService(s, hotelid);
		s.setId(id);

		return toServiceDto(s);
	}

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
	public List<RoomDto> findRooms(@PathVariable Long hotelid, @RequestParam("status") String status) {
		return toRoomDtos(roomService.findRooms(StatusConversor.toStatus(status), hotelid));
	}

	@PutMapping("/hotels/{hotelid}/rooms/{roomid}")
	public ResponseEntity updateRoom(@PathVariable Long hotelid, @PathVariable Long roomid,
			@RequestBody RoomDto roomDto) {
		Room r = toRoom(roomDto);
		r.getHotel().setId(hotelid);
		r.getType().setId(roomDto.getType().getId());
		r.setId(roomid);
		roomService.updateRoom(r);

		return ResponseEntity.noContent().build();
	}

	@PostMapping("/hotels/{hotelid}/reservations")
	public RoomTypeReservationDto addReservation(@PathVariable Long hotelid, @RequestBody RoomTypeReservationDto rtrDto)
			throws InstanceNotFoundException {

		RoomTypeReservation typer = toRoomTypeReservation(rtrDto);
		typer.getReservation().getUser().setId(rtrDto.getReservation().getUser().getId());

		typer.getHotel().setId(hotelid);
		typer.getRoomtype().setId(rtrDto.getRoomtype().getId());

		Long id = reservationService.addReservation(typer);

		rtrDto.setId(id);
		return rtrDto;

	}
	

	@GetMapping("/hotels/{hotelid}/reservations")
	public List<RoomTypeReservationDto> findAllReservations(@PathVariable Long hotelid,
			@RequestParam(defaultValue = "") String username) {
		List<RoomTypeReservationDto> result = new ArrayList<>();
		List<RoomTypeReservation> list;
		if (!username.isEmpty())
			list = reservationService.findReservations(username);
		else {
				list = reservationService.findReservationsHotel(hotelid);
		}
		for (RoomTypeReservation r : list) {
			result.add(toRoomTypeReservationDto(r));
		}

		return result;
	}

	@GetMapping("/hotels/{hotelid}/reservations/{id}")
	public RoomTypeReservationDto findReservation(@PathVariable Long hotelid, @PathVariable Long id)
			throws InstanceNotFoundException {
		return toRoomTypeReservationDto(reservationService.findById(id));
	}

	@PutMapping("/hotels/{hotelid}/reservations/{id}") 
	//Lanza siempre a exception non sei por que si os datos son correctos comprobado mediante o toString()
	public ResponseEntity updateReservation(@PathVariable Long hotelid, @PathVariable Long id,
			@RequestBody RoomTypeReservationDto rtrDto) throws InstanceNotFoundException, IncorrectHotelException {
				
		RoomTypeReservation typer = toRoomTypeReservation(rtrDto);
		System.out.println(typer.toString());
		typer.setId(rtrDto.getId());
		typer.getHotel().setId(rtrDto.getHotel().getId());
		typer.getRoomtype().setId(rtrDto.getRoomtype().getId());
		typer.getReservation().setId(rtrDto.getReservation().getId());
		typer.getReservation().getUser().setId(rtrDto.getReservation().getUser().getId());
		
		System.out.println(typer.toString());
		
		reservationService.updateReservation(typer);

		return ResponseEntity.noContent().build();
	}
}
