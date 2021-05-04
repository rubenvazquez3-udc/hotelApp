import { combineReducers } from 'redux';
import * as actionTypes from './actionTypes';

const initialState = {
    reservations: null,
    reservation: null
};

const reservations = ( state = initialState.reservations, action ) => {
    switch (action.type) {
        case actionTypes.ADD_RESERVATION_COMPLETED:
            return [...state, action.reservation];
        case actionTypes.FIND_RESERVATIONS_USER_COMPLETED:
            return action.reservations;
        case actionTypes.FIND_RESERVATIONS_HOTEL_COMPLETED:
            return action.reservations;
        default:
            return state;
    }
}
const reservation = (state = initialState.reservation, action) => {
    switch (action.type) {
        default:
            return state;
    }
}

const reducer = combineReducers({
    reservations,
    reservation,
});

export default reducer;