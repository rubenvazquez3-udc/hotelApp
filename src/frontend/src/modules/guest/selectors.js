
const getModuleState = state => state.guests;

export const getGuests = state =>
    getModuleState(state).guests;

export const getGuest = state =>
    getModuleState(state).guest;