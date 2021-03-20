import { useDispatch, useSelector } from "react-redux"
import React from 'react';

import * as selectors from '../selectors';
import * as actions from '../actions';
import { FormattedMessage } from "react-intl";
import Hotels from "./Hotels";

const FindHotelsResult = () => {
    const dispatch = useDispatch();

    //dispatch(actions.getHotels());

    const hotels = useSelector(selectors.getHotels);

    if(!hotels){
        return null;
    }

    if(hotels.length === 0){
        return(
            <div className="alert alert-danger" role="alert">
                <FormattedMessage id='project.hotels.noHotelsFound' />
            </div>

        ) 
    }

    return (
        <Hotels hotels={hotels}/>
    )


}

export default FindHotelsResult;