const getModuleState = state => state.services;

export const getServices = state => 
    getModuleState(state).service;

export const getService = state =>
    getModuleState(state).service;

export const getReservation = state =>
    getModuleState(state).reservation;