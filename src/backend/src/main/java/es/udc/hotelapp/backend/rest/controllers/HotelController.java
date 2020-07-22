package es.udc.hotelapp.backend.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import es.udc.hotelapp.backend.model.entities.Hotel;
import es.udc.hotelapp.backend.model.exceptions.HotelAlreadyExistsException;
import es.udc.hotelapp.backend.model.exceptions.InstanceNotFoundException;
import es.udc.hotelapp.backend.model.exceptions.ServiceAlreadyExistsException;
import es.udc.hotelapp.backend.model.services.HotelService;
import es.udc.hotelapp.backend.rest.common.ErrorsDto;
import es.udc.hotelapp.backend.rest.dtos.HotelDto;
import static es.udc.hotelapp.backend.rest.dtos.HotelConversor.toHotel;
import static es.udc.hotelapp.backend.rest.dtos.HotelConversor.toHotelDto;

import java.util.Locale;

@RestController
@RequestMapping("/")
public class HotelController {

	private final static String HOTEL_ALREADY_EXISTS_EXCEPTION_CODE = "project.exceptions.HotelAlreadyExistsException";

	private final static String SERVICE_ALREADY_EXISTS_EXCEPTION_CODE = "project.exceptions.ServiceAlreadyExistsException";
	@Autowired
	private HotelService hotelService;

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

	@PostMapping("/hotels")
	public HotelDto addHotel(@Validated @RequestBody HotelDto hotelDto) throws HotelAlreadyExistsException {
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

}
