import {config, appFetch} from './appFetch';

export const addReservation = (hotelid, reservation, onSuccess, onErrors) =>
    appFetch(`/hotels/${hotelid}/reservations`, config('POST', reservation), onSuccess, onErrors);


export const findReservations = ( hotelid, username, onSuccess, onErrors) => {
    let path = `/hotels/${hotelid}/reservations`;

    path+= username ? `?username=${username}` : "";

    appFetch(path, config('GET'), onSuccess, onErrors);
}

export const findReservationById = (hotelid, reservationid, onSuccess, onErrors) =>
    appFetch(`/hotels/${hotelid}/reservations/${reservationid}`, config('GET'), onSuccess, onErrors);

export const updateReservation = (hotelid, reservationid, reservation, onSuccess, onErrors) =>
    appFetch(`/hotels/${hotelid}/reservations/${reservationid}`, config('PUT', reservation), onSuccess, onErrors);

export const addGuest = (hotelid, reservationid, guest, onSuccess, onErrors) => 
    appFetch(`/hotels/${hotelid}/reservations/${reservationid}/guests`, config('POST', guest), onSuccess, onErrors);

export const findGuest = (hotelid, reservationid, guestid, onSuccess, onErrors) =>
    appFetch(`/hotels/${hotelid}/reservations/${reservationid}/guests/${guestid}`, config('GET'), onSuccess, onErrors);

export const updateGuest = (hotelid, reservationid, guestid, guest, onSuccess, onErrors) =>
    appFetch(`/hotels/${hotelid}/reservations/${reservationid}/guests/${guestid}`, config('PUT', guest), onSuccess, onErrors);

export const assignRoom = (hotelid, reservationid, roomreservation, onSuccess, onErrors) => 
    appFetch(`/hotels/${hotelid}/reservations/${reservationid}/assignRoom`, config('POST', roomreservation), onSuccess, onErrors);
