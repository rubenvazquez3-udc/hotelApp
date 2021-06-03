
import * as actionTypes from './actionTypes';
import backend from '../../backend';

const addGuestCompleted = guest => ({
    type: actionTypes.ADD_GUEST_COMPLETED,
    guest
});

export const addGuest = (guest, onSuccess, onErrors) => dispatch =>
    backend.reservationService.addGuest(guest.reservation.hotel.id, guest.reservation.id, guest, guest => {
        dispatch(addGuestCompleted(guest));
        onSuccess();
    },
        onErrors);

const findGuestsCompleted = guests => ({
    type: actionTypes.FIND_GUESTS_COMPLETED,
    guests
});

export const findGuests = (hotelid, username) => dispatch =>
    backend.reservationService.findGuests(hotelid, username, guests => {
        dispatch(findGuestsCompleted(guests));
    });