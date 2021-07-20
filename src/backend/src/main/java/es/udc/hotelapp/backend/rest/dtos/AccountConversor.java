package es.udc.hotelapp.backend.rest.dtos;

import java.util.List;
import java.util.stream.Collectors;

import es.udc.hotelapp.backend.model.entities.Account;
import es.udc.hotelapp.backend.model.entities.AccountItem;

public class AccountConversor {

	private AccountConversor() {
	}

	private final static AccountItemDto toAccountItemDto(AccountItem item) {

		return new AccountItemDto(item.getId(), item.getName(), item.getQuantity(), item.getItemPrice());
	}

	public final static AccountDto toAccountDto(Account account) {

		List<AccountItemDto> items = account.getItems().stream().map(i -> toAccountItemDto(i))
				.collect(Collectors.toList());

		return new AccountDto(account.getId(),
				RoomTypeReservationConversor.toRoomTypeReservationDto(account.getReservation()), items,
				account.getTotalPrice());

	}
}
