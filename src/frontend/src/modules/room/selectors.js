const getModuleState = state => state.rooms;

export const getRooms = () => 
    getModuleState(state).rooms;

export const getRoom = () =>
    getModuleState(state).room;
