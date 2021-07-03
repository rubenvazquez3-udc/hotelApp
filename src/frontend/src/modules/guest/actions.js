
import * as actionTypes from './actionTypes';
import backend from '../../backend';

const addGuestCompleted = guest => ({
    type: actionTypes.ADD_GUEST_COMPLETED,
    guest
});

export const addGuest = (guest, onSuccess, onErrors) => dispatch =>
    backend.reservationService.addGuest(guest.reservation.id, guest, guest => {
        dispatch(addGuestCompleted(guest));
        onSuccess();
    },
        onErrors);

const findGuestsCompleted = guests => ({
    type: actionTypes.FIND_GUESTS_COMPLETED,
    guests
});

export const previousFindGuestResultPage = criteria =>
        findGuests({...criteria, page: criteria.page-1});

export const nextFindGuestResultPage = criteria =>
        findGuests({...criteria, page: criteria.page +1});
        
const clearGuests = () => ({
    type: actionTypes.CLEAR_GUESTS_COMPLETED
});

export const findGuests = criteria => dispatch =>{
    dispatch(clearGuests());

    backend.reservationService.findGuests(criteria, guests => {
        dispatch(findGuestsCompleted({criteria,guests}));
    });
}
    