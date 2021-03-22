import React from 'react';
import { useSelector } from "react-redux"
import { FormattedMessage } from "react-intl";

import * as selectors from '../selectors';
import Hotels from "./Hotels";

const FindHotelsResult = () => {
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