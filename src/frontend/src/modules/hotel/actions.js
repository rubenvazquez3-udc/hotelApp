import * as actionTypes from './actionTypes';
import backend from '../../backend';

export const getHotelsCompleted = hotelResult => ({
    type: actionTypes.GET_HOTELS_COMPLETED,
    hotelResult
});

export const previousFindHotelsResultPage = criteria =>
    getHotels({...criteria, page: criteria.page-1});

export const nextFindHotelsResultPage = criteria =>
    getHotels({...criteria, page: criteria.page +1});

const clearHotels = () => ({
    type: actionTypes.CLEAR_HOTELS_COMPLETED
});


export const getHotels = criteria => dispatch => {
    dispatch(clearHotels());

    backend.hotelService.getHotels(criteria,hotelResult =>
        dispatch(getHotelsCompleted({criteria,hotelResult})));
};

const findHotelByIdCompleted = hotel => ({
    type: actionTypes.GET_HOTEL_BY_ID_COMPLETED,
    hotel
});

export const findHotelById = id => dispatch => {
    backend.hotelService.getHotelsById(id, hotel =>
        dispatch(findHotelByIdCompleted(hotel)));
};


const addHotelCompleted = authenticatedHotel => ({
    type: actionTypes.ADD_HOTEL_COMPLETED,
    authenticatedHotel
});

export const addHotel = (hotel, onSuccess, onErrors, reauthenticationCallback) => dispatch =>
    backend.hotelService.addHotel(hotel,
        authenticatedHotel => {
            dispatch(addHotelCompleted(authenticatedHotel));
            onSuccess();
        },
        onErrors,
        reauthenticationCallback
    );

const updateHotelCompleted = hotel => ({
    type: actionTypes.UPDATE_HOTEL_COMPLETED,
    hotel
});

export const updateHotel = (hotel, onSuccess, onErrors) => dispatch =>
    backend.hotelService.updateHotel(hotel,
        hotel => {
            dispatch(updateHotelCompleted(hotel));
            onSuccess();
        },
        onErrors);

const removeHotelCompleted = hotelid => ({
    type: actionTypes.REMOVE_HOTEL_COMPLETED,
    hotelid
});

export const removeHotel = (hotel, onSuccess, onErrors) => dispatch =>
    backend.hotelService.removeHotel(hotel, () => {
        dispatch(removeHotelCompleted(hotel.id));
        onSuccess();
    },
        onErrors);

const uploadPhotoCompleted = () => ({
    type: actionTypes.UPLOAD_PHOTO_COMPLETED
});

export const uploadPhoto = (hotelid, file, onSuccess, onErrors) => dispatch =>
    backend.hotelService.uploadPhoto(hotelid, file, () => {
        dispatch(uploadPhotoCompleted());
        onSuccess();
    }, onErrors);


const addPriceCompleted = () => ({
    type: actionTypes.ADD_PRICE_COMPLETED
});

export const addPrice = (price, onSuccess, onErrors) => dispatch =>
    backend.hotelService.addPrice(price, () => {
        dispatch(addPriceCompleted());
        onSuccess();
    }, onErrors);

const findPriceByIdCompleted = price => ({
    type: actionTypes.FIND_PRICE_BY_ID_COMPLETED,
    price
});

export const findPriceById = (hotelid, priceid) => dispatch =>
    backend.hotelService.findPriceById(hotelid, priceid, price =>
        dispatch(findPriceByIdCompleted(price)));

const updatePriceCompleted = () => ({
    type: actionTypes.UPDATE_PRICE_COMPLETED
});

export const updatePrice = (price, onSuccess, onErrors) => dispatch =>
    backend.hotelService.updatePrice(price, price =>{
        dispatch(updatePriceCompleted(price));
        onSuccess();
    }, onErrors);


