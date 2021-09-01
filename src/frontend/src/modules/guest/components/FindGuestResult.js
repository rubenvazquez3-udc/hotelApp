import React from 'react';
import { FormattedMessage } from "react-intl";
import { useSelector, useDispatch} from "react-redux"

import { Pager } from '../../common';

import * as selectors from '../selectors';
import * as actions from '../actions';
import Guests from './Guests';


const FindGuestResult = () => {

    const guestsSearch = useSelector(selectors.getGuests);
    const dispatch = useDispatch();

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
            <Guests guests={guestsSearch.guests.items} />
            <Pager
                back={{
                    enabled: guestsSearch.criteria.page >=1,
                    onClick: () => dispatch(actions.previousFindGuestResultPage(guestsSearch.criteria))
                }}

                next = {{
                    enabled: guestsSearch.guests.existMoreItems,
                    onClick: () => dispatch(actions.nextFindGuestResultPage(guestsSearch.criteria))
                }} />
        </div>

    );

}

export default FindGuestResult;