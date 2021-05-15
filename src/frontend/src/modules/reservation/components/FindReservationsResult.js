import React from 'react';
import { FormattedMessage } from "react-intl";
import { useSelector } from "react-redux"

import * as selectors from '../selectors';
import Reservations from './Reservations';


const FindReservationsResult = () => {

    const reservationSearch = useSelector(selectors.getReservations);

    if (!reservationSearch) {
        return null;
    }

    if (reservationSearch.length === 0) {
        return (
            <div className="alert alert-danger" role="alert" >
                <FormattedMessage id='project.reservation.FindReservationsResult.noReservationsFound' />
            </div>
        );
    }

    return (
        <div>
            <Reservations reservations={reservationSearch} />
        </div>

    );

}

export default FindReservationsResult;