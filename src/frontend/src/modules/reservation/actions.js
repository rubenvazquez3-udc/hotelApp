
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

const findReservationByIdCompleted = reservation => ({
    type: actionTypes.FIND_RESERVATION_BY_ID_COMPLETED,
    reservation
});

export const findReservationById = (hotelid, reservationid) => dispatch =>
    backend.reservationService.findReservationById(hotelid, reservationid, reservation =>{
        dispatch(findReservationByIdCompleted(reservation));
    });

const updateReservationCompleted = reservation => ({
    type: actionTypes.UPDATE_RESERVATION_COMPLETED,
    reservation
});

export const updateReservation = (reservation, onSuccess, onErrors) => dispatch =>
    backend.reservationService.updateReservation(reservation, reservation => {
        dispatch(updateReservationCompleted(reservation));
        onSuccess();
    }, onErrors);


const removeReservationCompleted = id =>({
    type: actionTypes.REMOVE_RESERVATION_COMPLETED,
    id
});

export const removeReservation = (reservation, id, onSuccess, onErrors) => dispatch => 
    backend.reservationService.removeReservation(reservation,
        dispatch(removeReservationCompleted(id)), onErrors);

    
const findAvailableRoomsCompleted = rooms => ({
    type: actionTypes.FIND_AVAILABLE_ROOMS_COMPLETED,
    rooms
});

export const findAvailableRooms = (hotelid, type) => dispatch => {

        backend.reservationService.findAvailableRooms(hotelid,type, 
            roomsfound => dispatch(findAvailableRoomsCompleted(roomsfound)));
    }


const assignRoomCompleted = roomreservation => ({
    type: actionTypes.ASSIGN_ROOM_COMPLETED
});

export const assignRoom = (roomreservation, onErrors) => dispatch => 
    backend.reservationService.assignRoom(roomreservation.reservation.hotel.id,roomreservation.reservation.id, roomreservation, reservation =>{
        dispatch(assignRoomCompleted(reservation));
    }, onErrors);


