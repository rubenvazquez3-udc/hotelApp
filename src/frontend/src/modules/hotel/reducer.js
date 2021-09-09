import { combineReducers } from 'redux';
import * as actionTypes from './actionTypes';

const initialState = {
    hotels: null,
    hotel: null,
    price: null
};

const hotels = (state = initialState.hotels, action) => {

    switch (action.type) {
        case actionTypes.GET_HOTELS_COMPLETED:
            return action.hotelResult;
        case actionTypes.ADD_HOTEL_COMPLETED:
            return  {criteria: {...state.criteria}, hotelResult: [...state.hotelResult.items, action.authenticatedHotel]};
        case actionTypes.UPDATE_HOTEL_COMPLETED:
            let hotels = [...state.hotelResult.items];
            hotels.splice(hotels.findIndex(hotel => hotel.id === action.hotel.id), 1, action.hotel);
            return  {criteria: {...state.criteria}, hotelResult: hotels};
        case actionTypes.REMOVE_HOTEL_COMPLETED:
            let hotels1 = [...state.hotelResult.items];
            let result = hotels1.filter(hotel => hotel.id !== action.hotelid);
            return  {criteria: {...state.criteria}, hotelResult: result};
        default:
            return state;
    }
}

const hotel = (state = initialState.hotel, action) => {
    switch (action.type) {
        case actionTypes.GET_HOTEL_BY_ID_COMPLETED:
            return action.hotel;
        default:
            return state;
    }
}

const price = (state= initialState.price, action) => {
    switch (action.type) {
        case actionTypes.FIND_PRICE_BY_ID_COMPLETED:
            return action.price;
        default:
            return state;
    }
}

const reducer = combineReducers({
    hotels,
    hotel,
    price
});

export default reducer;