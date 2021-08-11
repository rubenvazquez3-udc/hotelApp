import React from 'react';
import {useDispatch, useSelector} from "react-redux"
import { FormattedMessage } from "react-intl";

import * as selectors from '../selectors';
import Hotels from "./Hotels";
import {Pager} from "../../common";
import * as actions from "../actions";

const FindHotelsResult = () => {
    const hotelSearch = useSelector(selectors.getHotels);
    const dispatch = useDispatch();

    if (!hotelSearch) {
        return null;
    }

    if (hotelSearch.hotelResult.items.length === 0) {
        return (
            <div className="alert alert-danger" role="alert">
                <FormattedMessage id='project.hotels.noHotelsFound' />
            </div>

        )
    }

    return (
        <div>
        <Hotels hotels={hotelSearch.hotelResult.items} />

        <Pager
            back={{
            enabled: hotelSearch.criteria.page >=1,
            onClick: () => dispatch(actions.previousFindHotelsResultPage(hotelSearch.criteria))
            }}

            next= {{
            enabled: hotelSearch.hotelResult.existMoreItems,
            onClick: () => dispatch(actions.nextFindHotelsResultPage(hotelSearch.criteria))
            }} />
        </div>
    )


}

export default FindHotelsResult;