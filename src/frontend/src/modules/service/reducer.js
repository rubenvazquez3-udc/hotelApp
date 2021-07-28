import { combineReducers } from 'redux';
import * as actionTypes from './actionTypes';

const initialState = {
    services: null,
    service: null,
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


const service = (state = initialState.service, action) => {
    switch (action.type) {
        case actionTypes.FIND_SERVICE_BY_ID_COMPLETED:
            return action.service;
        default:
            return state;
    }
}

const services = (state = initialState.services, action) => {
    switch (action.type) {
        case actionTypes.ADD_SERVICE_COMPLETED:
            return {criteria: {...state.criteria}, services: [...state.services.items, action.service]};
        case actionTypes.FIND_SERVICES_COMPLETED:
            return action.services;
        case actionTypes.UPDATE_SERVICE_COMPLETED:
            let estado = [...state.services.items];
            estado.splice(estado.findIndex(service => service.id === action.service.id), 1, action.service);
            return {criteria: {...state.criteria}, services: estado};
        case actionTypes.REMOVE_SERVICE_COMPLETED:
            let list = [...state.services.items];
            let result = list.filter(service => service.id !== action.service);
            return {criteria: {...state.criteria}, services: result};
        case actionTypes.CLEAR_SERVICES_COMPLETED:
            return initialState.services;
            
        default:
            return state;
    }
}

const reducer = combineReducers({
    service,
    services,
    reservation,
});

export default reducer;