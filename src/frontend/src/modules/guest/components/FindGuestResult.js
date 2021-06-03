import React from 'react';
import { FormattedMessage } from "react-intl";
import { useSelector } from "react-redux"

import * as selectors from '../selectors';
import Guests from './Guests';


const FindGuestResult = () => {

    const guestsSearch = useSelector(selectors.getGuests);

    if (!guestsSearch) {
        return null;
    }

    if (guestsSearch.length === 0) {
        return (
            <div className="alert alert-danger" role="alert" >
                <FormattedMessage id='project.guests.FindReservationResult.noGuestsFound' />
            </div>
        );
    }

    return (
        <div>
            <Guests guests={guestsSearch} />
        </div>

    );

}

export default FindGuestResult;