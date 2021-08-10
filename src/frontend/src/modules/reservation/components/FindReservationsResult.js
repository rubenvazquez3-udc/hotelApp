import React from 'react';
import { FormattedMessage } from "react-intl";
import { useSelector, useDispatch } from "react-redux"
import { Pager } from '../../common';

import * as selectors from '../selectors';
import * as actions from '../actions';
import Reservations from './Reservations';


const FindReservationsResult = () => {

    const reservationSearch = useSelector(selectors.getReservations);
    const dispatch = useDispatch();

    if (!reservationSearch) {
        return null;
    }

    if (reservationSearch.reservations.items.length === 0) {
        return (
            <div className="alert alert-danger" role="alert" >
                <FormattedMessage id='project.reservations.FindReservationResult.noReservationsFound' />
            </div>
        );
    }

    return (
        <div>
            <Reservations reservations={reservationSearch.reservations.items} />
            <Pager
                back = {{
                    enabled : reservationSearch.criteria.page>=1,
                    onclick: () => dispatch(actions.previousFindReservationsResultPage(reservationSearch.criteria))
                }}

                next = {{
                    enabled: reservationSearch.reservations.existMoreItems,
                    onclick: () => dispatch(actions.nextFindReservationsResultPage(reservationSearch.criteria))

                }} />
        </div>

    );

}

export default FindReservationsResult;