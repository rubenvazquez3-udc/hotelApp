import { combineReducers } from 'redux';
import * as actionTypes from './actionTypes';

const initialState = {
    guests: null
};

const guests = (state = initialState.guests, action) => {
    switch (action.type) {
        case actionTypes.ADD_GUEST_COMPLETED:
            return {criteria: {...state.criteria}, guests: [...state.guests.items, action.guest]};
        case actionTypes.FIND_GUESTS_COMPLETED:
            return action.guests;
        
        case actionTypes.CLEAR_GUESTS_COMPLETED:
            return initialState.guests;

        default:
            return state;
    }
}



const reducer = combineReducers({
    guests
});

export default reducer;