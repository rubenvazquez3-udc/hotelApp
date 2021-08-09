const getModuleState = state => state.products;

export const getProducts = state => 
    getModuleState(state).products;

export const getProduct = state =>
    getModuleState(state).product;

export const getReservation = state =>
    getModuleState(state).reservation;