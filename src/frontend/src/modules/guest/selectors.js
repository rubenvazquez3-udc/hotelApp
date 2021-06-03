
const getModuleState = state => state.guests;

export const getGuests = state =>
    getModuleState(state).guests;
