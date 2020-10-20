import {config, appFetch} from './appFetch';

export const addRoom = (hotelid, room, onSuccess, onErrors) => 
    appFetch(`/hotels/${hotelid}/rooms`, config('POST', room), onSuccess, onErrors);

export const findRoom = (hotelid, roomid, onSuccess, onErrors) =>
    appFetch(`/hotels/${hotelid}/rooms/${roomid}`, config('GET'), onSuccess, onErrors);

export const findRooms = (hotelid, status, onSuccess, onErrors) => {
    let path = `/hotels/${hotelid}/rooms`;

    path+= stauts ? `?status=${status}` : "";


    appFetch(path, config('GET'), onSuccess, onErrors);
}

export const updateRoom = (hotelid, roomid, room, onSuccess, onErrors) => 
    appFetch(`/hotels/${hotelid}/rooms/${roomid}`, config('PUT', room), onSuccess, onErrors);

export const removeRoom = (hotelid, roomid, onSuccess, onErrors) => 
    appFetch(`/hotels/${hotelid}/rooms/${roomid}`, config('DELETE'), onSuccess, onErrors);