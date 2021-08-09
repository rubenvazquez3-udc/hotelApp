import * as actionTypes from './actionTypes';
import backend from '../../backend';

const addServiceCompleted = service => ({
    type: actionTypes.ADD_SERVICE_COMPLETED,
    service
});

export const addService = (service, onSuccess, onErrors) => dispatch =>
    backend.hotelService.addService(service,service => {
            dispatch(addServiceCompleted(service));
            onSuccess();
        },
        onErrors
    );

const findServicesCompleted = services => ({
    type: actionTypes.FIND_SERVICES_COMPLETED,
    services
});

export const previousFindServicesResultPage = criteria =>
        findServices({...criteria, page: criteria.page-1});

export const nextFindServicesResultPage = criteria =>
        findServices({...criteria, page: criteria.page +1});
        
const clearServices = () => ({
    type: actionTypes.CLEAR_SERVICES_COMPLETED
});

export const findServices = criteria => dispatch =>{
    dispatch(clearServices());
    backend.hotelService.findServices(criteria, services => {
        dispatch(findServicesCompleted({criteria, services}));
    })
}

const updateServiceCompleted = service => ({
    type: actionTypes.UPDATE_SERVICE_COMPLETED,
    service
});

export const updateService = (service, onSuccess, onErrors) => dispatch =>
    backend.hotelService.updateService(service, service => {
        dispatch(updateServiceCompleted(service));
        onSuccess();
    }, onErrors);

const findServiceByIdCompleted = service =>({
    type: actionTypes.FIND_SERVICE_BY_ID_COMPLETED,
    service
});

export const findServiceById = (hotelid, serviceid) => dispatch =>
    backend.hotelService.findServiceById(hotelid, serviceid, service => {
        dispatch(findServiceByIdCompleted(service));
    });

const removeServiceCompleted = service => ({
    type: actionTypes.REMOVE_SERVICE_COMPLETED,
    service
});

export const removeService = (service, onSuccess, onErrors) => dispatch =>
    backend.hotelService.removeService(service.hotel.id, service.id, () => {
        dispatch(removeServiceCompleted(service.id));
        onSuccess();
    }, onErrors);

const findReservationsByUserAndDateCompleted = reservations =>({
    type: actionTypes.RESERVATION_FOUND_COMPLETED,
    reservations
});

export const findReservationsByUserAndDate = (hotelid, userid, date) => dispatch =>
    backend.reservationService.findReservationsByUserAndDate(hotelid,userid,date, reservations => {
        dispatch(findReservationsByUserAndDateCompleted(reservations));
    });

