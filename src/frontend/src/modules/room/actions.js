import * as actionTypes from './actionTypes';
import backend from '../../backend';
import * as selectors from "./selectors";


const addRoomCompleted = authenticatedRoom => ({
    type: actionTypes.ADD_ROOM_COMPLETED,
    authenticatedRoom
});

export const addRoom = (room, onSuccess, onErrors) => dispatch =>
    backend.roomService.addRoom(room,
        authenticatedRoom => {
            dispatch(addRoomCompleted(authenticatedRoom));
            onSuccess();
        },
        onErrors
    );


const findRoomsCompleted = rooms => ({
    type: actionTypes.FIND_ROOMS_COMPLETED,
    rooms
});

export const findRooms = (hotelid, status, type) => dispatch =>
    backend.roomService.findRooms(hotelid, status,type, rooms => {
        dispatch(findRoomsCompleted(rooms));
    });

const findRoomByIdCompleted = room => ({
    type: actionTypes.FIND_ROOM_BY_ID_COMPLETED,
    room
})

export const findRoomById = (hotelid, roomid) => dispatch =>
    backend.roomService.findRoom(hotelid, roomid, room => {
        dispatch(findRoomByIdCompleted(room));
        });

const updateRoomCompleted = room => ({
    type: actionTypes.UPDATE_ROOM_COMPLETED,
    room
});

export const updateRoom = (room, onSuccess, onErrors) => dispatch =>
    backend.roomService.updateRoom(room,
        room => {
            dispatch(updateRoomCompleted(room));
            onSuccess();
        },
        onErrors);

const findAllRoomTypesCompleted = roomtypes => ({
    type: actionTypes.FIND_ALL_ROOM_TYPES_COMPLETED,
    roomtypes
});

export const findAllRoomTypes = () => (dispatch, getState) => {
    const roomtypes = selectors.getRoomTypes(getState());

    if(!roomtypes) {
        backend.roomService.findAllRoomTypes(
            roomtypes => dispatch(findAllRoomTypesCompleted(roomtypes))
        );
    }
}

const removeRoomCompleted = room => ({
    type: actionTypes.REMOVE_ROOM_COMPLETED,
    room
});

export const removeRoom = (room,roomid, onSuccess, onErrors) => dispatch => 
    backend.roomService.removeRoom(room.hotel.id, room.id,
        dispatch(removeRoomCompleted(roomid)), onErrors);