import {combineReducers} from 'redux';
import * as actionTypes from './actionTypes';

const initialState = {
    hotels : null
};

const hotels = (state = initialState.hotels, action) => {

    switch(action.type){
        case hotels.actionTypes.GET_HOTELS_COMPLETED:
            return action.getHotels;
    }
}

const reducer = combineReducers({
    hotels
});

export default reducer;