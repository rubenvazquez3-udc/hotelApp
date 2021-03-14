import {combineReducers} from 'redux';
import * as actionTypes from './actionTypes';

const initialState = {
    rooms: null,
    room: null
};

const room = (state = initialState.room, action) => {
    switch (action.type) {
        case actionTypes.FIND_ROOM_BY_ID_COMPLETED:
            return action.room;
        case actionTypes.UPDATE_ROOM_COMPLETED:
            return action.room;
    
        default:
            return state;
    }
}

const rooms = (state = initialState.rooms, action) => {
    switch (action.type) {
        case actionTypes.ADD_ROOM_COMPLETED:
            return action.hotels;
    
        default:
            return state;
    }
}

const reducer = combineReducers({
    rooms,
    room,
});

export default reducer;