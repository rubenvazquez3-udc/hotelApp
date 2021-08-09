import { combineReducers } from 'redux';
import * as actionTypes from './actionTypes';

const initialState = {
    reservations: null,
    reservation: null,
    availableRooms: null,
    account: null
};

const reservations = ( state = initialState.reservations, action ) => {
    switch (action.type) {

        case actionTypes.ADD_RESERVATION_COMPLETED:
            return [...state, action.reservation];

        case actionTypes.FIND_RESERVATIONS_USER_COMPLETED:
            return action.reservations;

        case actionTypes.FIND_RESERVATIONS_HOTEL_COMPLETED:
            return action.reservations;

        case actionTypes.UPDATE_RESERVATION_COMPLETED:
            let estado = [...state];
            estado.splice(estado.findIndex(r => r.id === action.reservation.id), 1, action.reservation);
            return estado;

            case actionTypes.REMOVE_RESERVATION_COMPLETED:
                let reservations = [...state];
                let result = reservations.filter(reservation => reservation.id !== action.id);
                return result;

        default:
            return state;
    }
}
const reservation = (state = initialState.reservation, action) => {
    switch (action.type) {
        case actionTypes.FIND_RESERVATION_BY_ID_COMPLETED:
            return action.reservation;
        default:
            return state;
    }
}

const availableRooms = (state = initialState.availableRooms, action) =>{

    switch (action.type) {
        case actionTypes.FIND_AVAILABLE_ROOMS_COMPLETED:
            return action.rooms;
    
        default:
            return state;
    }

}

const account = (state= initialState.account, action) => {

    switch (action.type) {
        case actionTypes.ADD_TO_ACCOUNT_COMPLETED:
            return action.account;
        
        case actionTypes.FIND_ACCOUNT_COMPLETED:
            return action.account;
        default:
            return state;
    }

}

const reducer = combineReducers({
    reservations,
    reservation,
    availableRooms,
    account
});

export default reducer;