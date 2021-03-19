
const getModuleState = state => state.hotels;

export const getHotels = state =>
    getModuleState(state).hotels;

export const getHotel = state =>
    getModuleState(state).hotel;