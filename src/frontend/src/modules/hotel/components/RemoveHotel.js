import React from 'react';
import { FormattedMessage } from "react-intl";

import { useDispatch, useSelector} from "react-redux"

import * as actions from '../actions';
import * as selectors from "../selectors";
import {ConfirmDialog} from "../../common";


const RemoveHotel = () =>{

    const dispatch = useDispatch();
    const header = <FormattedMessage id='project.hotels.RemoveHotel.title'/>;
    const hotel = useSelector(selectors.getHotel);
    const body = <FormattedMessage id='project.hotels.RemoveHotel.body'/> + '?';


    const handleSubmit = event => {
        event.preventDefault();

         dispatch(actions.removeHotel(hotel));
    }

    return (
        <ConfirmDialog id='removeHotel' icon='exclamation-triangle' headerTitle={header} bodyTitle={body} onConfirm={e => handleSubmit(e)} />
    )

}

export default RemoveHotel;