const getModuleState = state => state.rooms;

export const getRooms = state => 
    getModuleState(state).rooms;

export const getRoom = state =>
    getModuleState(state).room;

export const getRoomTypes = state =>
    getModuleState(state).roomtypes;
