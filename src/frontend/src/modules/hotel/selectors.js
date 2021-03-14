
const getModuleState = state => state.hotels;

export const getHotels = state =>
    getModuleState(state).hotelResult;

export const getHotel = state =>
    getModuleState(state).hotel;