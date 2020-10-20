import * as actionTypes from './actionTypes';
import backend from '../../backend';


export const getHotelsCompleted = hotel => ({
    type: actionTypes.GET_HOTELS_COMPLETED, hotel
});

export const getHotels =  dispatch => backend.hotelService.getHotels() => {
    dispatch(getHotelsCompleted);
    onSuccess();
}