import {config, appFetch} from './appFetch';

export const addRoom = (room, onSuccess, onErrors) => 
    appFetch(`/hotels/${room.hotel.id}/rooms`, config('POST', room), onSuccess, onErrors);

export const findRoom = (hotelid, roomid, onSuccess, onErrors) =>
    appFetch(`/hotels/${hotelid}/rooms/${roomid}`, config('GET'), onSuccess, onErrors);

export const findRooms = ({hotelid, status, type,page}, onSuccess, onErrors) => {
    let path = `/hotels/${hotelid}/rooms/?page=${page}`;

    path+= status ? `&status=${status}` : "";

    path+= type ? `&type=${type}` : "";

    appFetch(path, config('GET'), onSuccess, onErrors);
}

export const findAllRoomTypes = (onSuccess) => 
    appFetch('/hotels/roomtypes', config('GET'), onSuccess);


export const updateRoom = (room, onSuccess, onErrors) => 
    appFetch(`/hotels/${room.hotel.id}/rooms/${room.id}`, config('PUT', room), onSuccess, onErrors);

export const removeRoom = (hotelid, roomid, onSuccess, onErrors) => 
    appFetch(`/hotels/${hotelid}/rooms/${roomid}`, config('DELETE'), onSuccess, onErrors);