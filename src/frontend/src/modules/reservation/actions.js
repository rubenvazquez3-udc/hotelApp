
import * as actionTypes from './actionTypes';
import backend from '../../backend';

const addReservationCompleted = reservation => ({
    type: actionTypes.ADD_RESERVATION_COMPLETED,
    reservation
});

export const addReservation = (reservation, onSuccess, onErrors) => dispatch =>
    backend.reservationService.addReservation(reservation, reservation => {
        dispatch(addReservationCompleted(reservation));
        onSuccess();
    },
    onErrors
    );

const findReservationsUserCompleted = reservations => ({
    type: actionTypes.FIND_RESERVATIONS_USER_COMPLETED,
    reservations
});

export const findReservationsUser = (username) => dispatch =>
    backend.reservationService.findReservationsUser(username, reservations => {
        dispatch(findReservationsUserCompleted(reservations));
    }
   );

const findReservationsHotelCompleted = reservations => ({
    type: actionTypes.FIND_RESERVATIONS_HOTEL_COMPLETED,
    reservations
});

export const findReservationsHotel = (hotelid, username, date) => dispatch =>
    backend.reservationService.findReservationsHotel(hotelid, username, date, reservations => {
        dispatch(findReservationsHotelCompleted(reservations));
    });
