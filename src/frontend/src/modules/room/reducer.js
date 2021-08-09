import { combineReducers } from 'redux';
import * as actionTypes from './actionTypes';

const initialState = {
    roomtypes: null,
    rooms: null,
    room: null
};

const roomtypes = (state = initialState.roomtypes, action) => {
    switch (action.type) {
        case actionTypes.FIND_ALL_ROOM_TYPES_COMPLETED:
            return action.roomtypes;
        default:
            return state;
    }
}

const room = (state = initialState.room, action) => {
    switch (action.type) {
        case actionTypes.FIND_ROOM_BY_ID_COMPLETED:
            return action.room;
        default:
            return state;
    }
}

const rooms = (state = initialState.rooms, action) => {
    switch (action.type) {
        case actionTypes.ADD_ROOM_COMPLETED:
            return {criteria: {...state.criteria}, rooms: [...state.rooms.items, action.authenticatedRoom]};
        case actionTypes.FIND_ROOMS_COMPLETED:
            return action.rooms;
        case actionTypes.UPDATE_ROOM_COMPLETED:
            console.log(action.room);
            console.log(state.rooms);
            let estado = [...state.rooms.items];
            estado.splice(estado.findIndex(room => room.id === action.room.id), 1, action.room);
            return {criteria: {...state.criteria}, rooms: estado};
        case actionTypes.REMOVE_ROOM_COMPLETED:
            let list = [...state.rooms.items];
            let result = list.filter(room => room.id !== action.room);
            return {criteria: {...state.criteria}, rooms: result};
        case actionTypes.CLEAR_ROOMS_COMPLETED:
            return initialState.rooms;
            
        default:
            return state;
    }
}

const reducer = combineReducers({
    roomtypes,
    rooms,
    room,
});

export default reducer;