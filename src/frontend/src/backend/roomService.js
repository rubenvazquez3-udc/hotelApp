import {config, appFetch} from './appFetch';

export const addRoom = (room, onSuccess, onErrors) => 
    appFetch(`/hotels/${room.hotel.id}/rooms`, config('POST', room), onSuccess, onErrors);

export const findRoom = (hotelid, roomid, onSuccess, onErrors) =>
    appFetch(`/hotels/${hotelid}/rooms/${roomid}`, config('GET'), onSuccess, onErrors);

export const findRooms = (hotelid, status, onSuccess, onErrors) => {
    let path = `/hotels/${hotelid}/rooms`;

    path+= status ? `?status=${status}` : "";


    appFetch(path, config('GET'), onSuccess, onErrors);
}

export const updateRoom = (room, onSuccess, onErrors) => 
    appFetch(`/hotels/${room.hotel.id}/rooms/${room.id}`, config('PUT', room), onSuccess, onErrors);

export const removeRoom = (hotelid, roomid, onSuccess, onErrors) => 
    appFetch(`/hotels/${hotelid}/rooms/${roomid}`, config('DELETE'), onSuccess, onErrors);