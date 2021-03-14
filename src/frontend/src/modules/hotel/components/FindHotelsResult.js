import { useSelector } from "react-redux"
import React from 'react';

import * as selectors from '../selectors';
import { FormattedMessage } from "react-intl";
import Hotels from "./Hotels";

const FindHotelsResult = () => {

    const hotels = useSelector(selectors.getHotels);

    //console.log(hotelResult);

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
        <Hotels hotels={hotels.hotels.items}/>
    )


}

export default FindHotelsResult;