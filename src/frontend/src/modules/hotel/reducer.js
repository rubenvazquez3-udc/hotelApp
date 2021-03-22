import {combineReducers} from 'redux';
import * as actionTypes from './actionTypes';

const initialState = {
    hotels: null,
    hotel: null
};

const hotels = (state = initialState.hotels, action) => {

    switch(action.type){
        case actionTypes.GET_HOTELS_COMPLETED:
            return action.hotelResult;
        case actionTypes.ADD_HOTEL_COMPLETED:
            return [...state, action.authenticatedHotel];
        default:
             return state;
    }
}

const hotel = (state = initialState.hotel, action) => {
        switch (action.type) {
            case actionTypes.GET_HOTEL_BY_ID_COMPLETED:
                return action.hotel;
            case actionTypes.UPDATE_HOTEL_COMPLETED:
                return action.hotel;
            case actionTypes.REMOVE_HOTEL_COMPLETED:
                return initialState.hotel;
            default:
                return state;
        }
}

const reducer = combineReducers({
    hotels,
    hotel
});

export default reducer;