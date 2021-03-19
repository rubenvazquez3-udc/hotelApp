import {config, appFetch} from './appFetch';


export const addHotel = (hotel, onSuccess, onErrors) =>
    appFetch(`/hotels`, config('POST', hotel), onSuccess, onErrors);


export const getHotels = (onSuccess) => {
    appFetch(`/hotels/`, config('GET'), onSuccess);
}

export const getHotelsById = (hotelid, onSuccess, onErrors) => {
    appFetch(`/hotels/${hotelid}`, config('GET'), onSuccess, onErrors);
}

export const updateHotel = (hotel, onSuccess, onErrors) =>{
    appFetch(`/hotels/${hotel.id}`, config('PUT', hotel), onSuccess, onErrors);
}

export const removeHotel = (hotel, onSuccess, onErrors) =>{
    appFetch(`/hotels/${hotel.id}`, config('DELETE'), onSuccess, onErrors);
}

export const addService = (hotelid, service, onSuccess, onErrors) =>{
    appFetch(`/hotels/${hotelid}/services`, config('POST', service), onSuccess,onErrors);
}

export const findServices = (hotelid, onSuccess, onErrors) =>{
    appFetch(`/hotels/${hotelid}/services`, config('GET'), onSuccess, onErrors);
}

export const removeService = (hotelid, serviceid, onSuccess, onErrors) =>
    appFetch(`/hotels/${hotelid}/services/${serviceid}`, config('DELETE'), onSuccess, onErrors);


export const updateService = (hotelid, serviceid, service, onSuccess, onErrors) =>
    appFetch(`/hotels/${hotelid}/services/${serviceid}`, config('PUT', service), onSuccess, onErrors);



