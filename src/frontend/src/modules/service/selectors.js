const getModuleState = state => state.services;

export const getServices = state => 
    getModuleState(state).services;

export const getService = state =>
    getModuleState(state).service;

export const getReservation = state =>
    getModuleState(state).reservation;