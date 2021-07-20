import {config, appFetch} from './appFetch';

export const addReservation = (reservation, onSuccess, onErrors) =>
    appFetch(`/reservations`, config('POST', reservation), onSuccess, onErrors);


export const findReservations = ( hotelid, username, date, onSuccess, onErrors) => {
    let path = `/reservations/`;

    path+= hotelid ? `?hotelid=${hotelid}` : "";

    path+= username ?  hotelid ? `&username=${username}` : `?username=${username}` : "";

    path+= date ? `&date=${date}` : "";

    appFetch(path, config('GET'), onSuccess, onErrors);
}

export const findReservationById = (reservationid, onSuccess, onErrors) =>
    appFetch(`/reservations/${reservationid}`, config('GET'), onSuccess, onErrors);

export const updateReservation = (reservation, onSuccess, onErrors) =>
    appFetch(`/reservations/${reservation.id}`, config('PUT', reservation), onSuccess, onErrors);

export const addGuest = (reservationid, guest, onSuccess, onErrors) => 
    appFetch(`/reservations/${reservationid}/guests`, config('POST', guest), onSuccess, onErrors);


export const assignRoom = ( reservationid, roomreservation, onSuccess, onErrors) => 
    appFetch(`/reservations/${reservationid}/assignRoom`, config('POST', roomreservation), onSuccess, onErrors);

export const removeReservation = (reservation, onSuccess, onErrors) =>
        appFetch(`/reservations/${reservation.id}`, config('DELETE'), onSuccess, onErrors);
    


export const findGuests = ({hotelid, username, page}, onSuccess, onErrors) =>{

    let path = `/hotels/${hotelid}/guests/?page=${page}`;

    path += username ? `&username=${username}` : "";

    appFetch(path, config('GET'), onSuccess, onErrors);
}

export const findAvailableRooms = (hotelid, type, onSuccess, onErrors) =>
    appFetch(`/hotels/${hotelid}/rooms/?type=${type}&status=LIBRE`, config('GET'),onSuccess, onErrors);

