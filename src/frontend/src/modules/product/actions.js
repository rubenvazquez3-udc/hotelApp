import * as actionTypes from './actionTypes';
import backend from '../../backend';

const addProductCompleted = product => ({
    type: actionTypes.ADD_PRODUCT_COMPLETED,
    product
});

export const addProduct = (product, onSuccess, onErrors) => dispatch =>
    backend.hotelService.addProduct(product,product => {
            dispatch(addProductCompleted(product));
            onSuccess();
        },
        onErrors
    );

const findProductsCompleted = products => ({
    type: actionTypes.FIND_PRODUCTS_COMPLETED,
    products
});

export const previousFindProductsResultPage = criteria =>
        findProducts({...criteria, page: criteria.page-1});

export const nextFindProductsResultPage = criteria =>
        findProducts({...criteria, page: criteria.page +1});
        
const clearProducts = () => ({
    type: actionTypes.CLEAR_PRODUCTS_COMPLETED
});

export const findProducts = criteria => dispatch =>{
    dispatch(clearProducts());
    backend.hotelService.findProducts(criteria, products => {
        dispatch(findProductsCompleted({criteria, products}));
    })
}

const updateProductCompleted = product => ({
    type: actionTypes.UPDATE_PRODUCT_COMPLETED,
    product
});

export const updateProduct = (product, onSuccess, onErrors) => dispatch =>
    backend.hotelService.updateProduct(product, product => {
        dispatch(updateProductCompleted(product));
        onSuccess();
    }, onErrors);

const findProductByIdCompleted = product =>({
    type: actionTypes.FIND_PRODUCT_BY_ID_COMPLETED,
    product
});

export const findProductById = (hotelid, productid) => dispatch =>
    backend.hotelService.findServiceById(hotelid, productid, product => {
        dispatch(findProductByIdCompleted(product));
    });

const removeProductCompleted = product => ({
    type: actionTypes.REMOVE_PRODUCT_COMPLETED,
    product
});

export const removeProduct = (product, onSuccess, onErrors) => dispatch =>
    backend.hotelService.removeProduct(product.hotel.id, product.id, () => {
        dispatch(removeProductCompleted(product.id));
        onSuccess();
    }, onErrors);

const findReservationsByUserAndDateCompleted = reservations =>({
    type: actionTypes.RESERVATION_FOUND_COMPLETED,
    reservations
});

export const findReservationsByUserAndDate = (hotelid, userid, date) => dispatch =>
    backend.reservationService.findReservationsByUserAndDate(hotelid,userid,date, reservations => {
        dispatch(findReservationsByUserAndDateCompleted(reservations));
    });
