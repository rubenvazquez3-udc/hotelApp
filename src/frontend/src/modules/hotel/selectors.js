
const getModuleState = state => state.hotel;

export const getHotels = state =>
    getModuleState(state).hotels;