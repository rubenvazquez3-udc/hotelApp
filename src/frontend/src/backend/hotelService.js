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
/************************************************ PRODUCTS & SERVICES *********************************************** */
export const addService = (service, onSuccess, onErrors) =>{
    appFetch(`/hotels/${service.hotel.id}/services`, config('POST', service), onSuccess,onErrors);
}

export const addProduct = (product, onSuccess, onErrors) =>{
    appFetch(`/hotels/${product.hotel.id}/products`, config('POST', product), onSuccess,onErrors);
}

export const findServices = ({hotelid, page}, onSuccess, onErrors) =>{

    let path = `/hotels/${hotelid}/services/?page=${page}`;

    appFetch(path, config('GET'), onSuccess, onErrors);

}

export const findProducts = ({hotelid, page}, onSuccess, onErrors) =>{
    
    let path = `/hotels/${hotelid}/products/?page=${page}`;
    
    appFetch(path, config('GET'), onSuccess, onErrors);

}
export const findServiceById = (hotelid, serviceid, onSuccess, onErrors) => 
    appFetch(`/hotels/${hotelid}/services/${serviceid}`, config('GET'), onSuccess, onErrors);

export const findProductById = (hotelid, productid, onSuccess, onErrors) => 
    appFetch(`/hotels/${hotelid}/products/${productid}`, config('GET'), onSuccess, onErrors);

export const updateService = (service, onSuccess, onErrors) =>
    appFetch(`/hotels/${service.hotel.id}/services/${service.id}`, config('PUT', service), onSuccess, onErrors);

export const updateProduct = (product, onSuccess, onErrors) =>
    appFetch(`/hotels/${product.hotel.id}/products/${product.id}`, config('PUT', product), onSuccess, onErrors);

export const removeService = (hotelid, serviceid, onSuccess, onErrors) =>
    appFetch(`/hotels/${hotelid}/services/${serviceid}`, config('DELETE'), onSuccess, onErrors);

export const removeProduct = (hotelid, productid, onSuccess, onErrors) =>
    appFetch(`/hotels/${hotelid}/products/${productid}`, config('DELETE'), onSuccess, onErrors);

/**************************************************** PHOTOS **************************************************** */

export const uploadPhoto = (hotelid, file, onSuccess, onErrors) =>
    appFetch(`/hotels/${hotelid}/upload-photo`, config('POST', file), onSuccess, onErrors);

/************************************************ HOTEL PRICES ************************************************** */

export const addPrice = (price, onSuccess, onErrors) =>
    appFetch(`/hotels/${price.hotel.id}/prices`, config('POST', price), onSuccess, onErrors);

export const updatePrice = (price, onSuccess, onErrors) =>
    appFetch(`/hotels/${price.hotel.id}/prices/${price.id}`, config('PUT', price), onSuccess, onErrors);

export const findPriceById = (hotelid, priceid, onSuccess, onErrors) =>
    appFetch(`/hotels/${hotelid}/prices/${priceid}`, config('GET'), onSuccess, onErrors);

export const removePrice = (hotelid, priceid, onSuccess, onErrors) =>
    appFetch(`/hotels/${hotelid}/prices/${priceid}`, config('DELETE'), onSuccess, onErrors);



