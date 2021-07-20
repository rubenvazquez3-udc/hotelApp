package es.udc.hotelapp.backend.rest.controllers;

import static es.udc.hotelapp.backend.rest.dtos.GuestReservationConversor.*;
import static es.udc.hotelapp.backend.rest.dtos.RoomReservationConversor.*;
import static es.udc.hotelapp.backend.rest.dtos.RoomTypeReservationConversor.*;
import static es.udc.hotelapp.backend.rest.dtos.AccountConversor.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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

import es.udc.hotelapp.backend.model.entities.Account;
import es.udc.hotelapp.backend.model.entities.GuestReservation;
import es.udc.hotelapp.backend.model.entities.RoomReservation;
import es.udc.hotelapp.backend.model.entities.RoomTypeReservation;
import es.udc.hotelapp.backend.model.exceptions.IncorrectReservationException;
import es.udc.hotelapp.backend.model.exceptions.InstanceNotFoundException;
import es.udc.hotelapp.backend.model.exceptions.PermissionException;
import es.udc.hotelapp.backend.model.services.ReservationService;
import es.udc.hotelapp.backend.rest.common.ErrorsDto;
import es.udc.hotelapp.backend.rest.dtos.AccountDto;
import es.udc.hotelapp.backend.rest.dtos.AddToAccountParams;
import es.udc.hotelapp.backend.rest.dtos.GuestReservationDto;
import es.udc.hotelapp.backend.rest.dtos.RoomReservationDto;
import es.udc.hotelapp.backend.rest.dtos.RoomTypeReservationDto;

@RestController
@RequestMapping("/reservations")
public class ReservationController {
	
	private final static String INCORRECT_RESERVATION_EXCEPTION_CODE = "project.exceptions.IncorrectReservationException";

	@Autowired
	private ReservationService reservationService;
	
	@Autowired
	private MessageSource messageSource;
	
	@ExceptionHandler(IncorrectReservationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorsDto handleIncorrectReservationException(IncorrectReservationException exception, Locale locale) {
		String errorMessage = messageSource.getMessage(INCORRECT_RESERVATION_EXCEPTION_CODE, null,
				INCORRECT_RESERVATION_EXCEPTION_CODE , locale);
		return new ErrorsDto(errorMessage);

	}
	
	
	@PostMapping("")
	public RoomTypeReservationDto addReservation(@RequestBody RoomTypeReservationDto rtrDto)
			throws InstanceNotFoundException, PermissionException, IncorrectReservationException {

		RoomTypeReservation typer = toRoomTypeReservation(rtrDto);
		
		typer.getUser().setId(rtrDto.getUser().getId());
		typer.getHotel().setId(rtrDto.getHotel().getId());
		typer.getRoomtype().setId(rtrDto.getRoomtype().getId());
		
		reservationService.addReservation(typer);
		
		reservationService.createAccount(new Account(typer));		
		
		rtrDto.setId(typer.getId());

		return rtrDto;

	}

	@GetMapping("")
	public List<RoomTypeReservationDto> findAllReservations(@RequestParam(required = false) Long hotelid,
			@RequestParam(defaultValue = "") String username, @RequestParam(defaultValue = "") String date) {
		List<RoomTypeReservationDto> result = new ArrayList<>();
		List<RoomTypeReservation> list = reservationService.findReservations(hotelid, date, username);
		
		for (RoomTypeReservation r : list) {
			result.add(toRoomTypeReservationDto(r));
		}

		return result;
	}

	@GetMapping("/{id}")
	public RoomTypeReservationDto findReservation(@PathVariable Long id)
			throws InstanceNotFoundException {
		return toRoomTypeReservationDto(reservationService.findById(id));
	}

	@PutMapping("/{id}")
	public RoomTypeReservationDto updateReservation(@PathVariable Long id,
			@RequestBody RoomTypeReservationDto rtrDto) throws InstanceNotFoundException {
		
		RoomTypeReservation typer = toRoomTypeReservation(rtrDto);
		
		typer.setId(id);
		typer.getHotel().setId(rtrDto.getHotel().getId());
		typer.getRoomtype().setId(rtrDto.getRoomtype().getId());
		typer.getUser().setId(rtrDto.getUser().getId());

		reservationService.updateReservation(typer);

		return rtrDto;
	}

	@PostMapping("/{id}/guests")
	public GuestReservationDto addGuest(@PathVariable Long id,
			@RequestBody GuestReservationDto grDto) throws IncorrectReservationException {
		GuestReservation gr = toGuestReservation(grDto);
		gr.getReservation().setId(grDto.getReservation().getId());

		reservationService.addGuest(gr);
		
		grDto.setId(gr.getId());
		grDto.getGuest().setId(gr.getGuest().getId());
		
		return grDto;
	}

	@PostMapping("/{id}/assignRoom")
	public RoomReservationDto assignRoom(@PathVariable Long id,
			@RequestBody RoomReservationDto rrDto) throws InstanceNotFoundException, IncorrectReservationException {
		RoomReservation rr = toRoomReservation(rrDto);

		rr.getReservation().setId(rrDto.getReservation().getId());
		rr.getRoom().setId(rrDto.getRoom().getId());
		rr.getRoom().getType().setId(rrDto.getRoom().getType().getId());
		rr.getRoom().getHotel().setId(rrDto.getRoom().getHotel().getId());

		RoomReservation rr1 = reservationService.assignReservation(rr, id);
		
		rrDto.setId(rr1.getId());
		return rrDto;

	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> removeReservation( @PathVariable Long id) throws InstanceNotFoundException {

		reservationService.removeReservation(id);
		
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{id}/account")
	public AccountDto getAccount ( @PathVariable Long id) {
		return toAccountDto(reservationService.findAccount(id));
	}
	
	@GetMapping("/account")
	public List<RoomTypeReservationDto> findReservations(@RequestParam(required = true) Long hotelid,
			@RequestParam(required = true) Long userid, @RequestParam(defaultValue = "") String date) {
		List<RoomTypeReservationDto> result = new ArrayList<>();
		List<RoomTypeReservation> list = reservationService.findReservations(hotelid, userid, date);
		
		for (RoomTypeReservation r : list) {
			result.add(toRoomTypeReservationDto(r));
		}

		return result;
	}
	
	@PostMapping("/{id}/account")
	public AccountDto addToAccount( @PathVariable Long id, @RequestBody AddToAccountParams params) throws InstanceNotFoundException {
		
		return toAccountDto(reservationService.addToAccount(params.getServiceId(), params.getProductId(), id, params.getQuantity()));
	}
}
