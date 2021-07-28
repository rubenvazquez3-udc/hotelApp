
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

const findReservationsCompleted = reservations => ({
    type: actionTypes.FIND_RESERVATIONS_HOTEL_COMPLETED,
    reservations
});

export const findReservations = (hotelid, username, date) => dispatch =>
    backend.reservationService.findReservations(hotelid, username, date, reservations => {
        dispatch(findReservationsCompleted(reservations));
    });

const findReservationByIdCompleted = reservation => ({
    type: actionTypes.FIND_RESERVATION_BY_ID_COMPLETED,
    reservation
});

export const findReservationById = (reservationid) => dispatch =>
    backend.reservationService.findReservationById(reservationid, reservation =>{
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

export const removeReservation = (reservation, onSuccess, onErrors) => dispatch => 
    backend.reservationService.removeReservation(reservation,() =>{
        dispatch(removeReservationCompleted(reservation.id));
        onSuccess();
    }, onErrors);

    
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
    backend.reservationService.assignRoom(roomreservation.reservation.id, roomreservation, reservation =>{
        dispatch(assignRoomCompleted(reservation));
    }, onErrors);


