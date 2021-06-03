const getModuleState = state => state.reservations;

export const getReservations = state => 
    getModuleState(state).reservations;

export const getReservation = state =>
    getModuleState(state).reservation;

export const getAvailableRooms = state =>
    getModuleState(state).availableRooms;