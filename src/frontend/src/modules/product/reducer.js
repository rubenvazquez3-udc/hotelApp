import { combineReducers } from 'redux';
import * as actionTypes from './actionTypes';

const initialState = {
    products: null,
    product: null,
    reservation: null
};

const reservation = (state = initialState.reservation, action) =>{
    switch (action.type) {
        case actionTypes.RESERVATION_FOUND_COMPLETED:
            return action.reservations;
        default:
            return state;
    }
}


const product = (state = initialState.product, action) => {
    switch (action.type) {
        case actionTypes.FIND_PRODUCT_BY_ID_COMPLETED:
            return action.product;
        default:
            return state;
    }
}

const products = (state = initialState.products, action) => {
    switch (action.type) {
        case actionTypes.ADD_PRODUCT_COMPLETED:
            return {criteria: {...state.criteria}, products: [...state.products.items, action.product]};
        case actionTypes.FIND_PRODUCTS_COMPLETED:
            return action.products;
        case actionTypes.UPDATE_PRODUCT_COMPLETED:
            let estado = [...state.products.items];
            estado.splice(estado.findIndex(product => product.id === action.product.id), 1, action.product);
            return {criteria: {...state.criteria}, services: estado};
        case actionTypes.REMOVE_PRODUCT_COMPLETED:
            let list = [...state.services.items];
            let result = list.filter(product => product.id !== action.product);
            return {criteria: {...state.criteria}, products: result};
        case actionTypes.CLEAR_PRODUCTS_COMPLETED:
            return initialState.products;
            
        default:
            return state;
    }
}

const reducer = combineReducers({
    product,
    products,
    reservation,
});

export default reducer;