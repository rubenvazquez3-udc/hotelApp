import React, { useEffect  } from 'react';
import { useDispatch, useSelector } from "react-redux"

import * as actions from '../actions';
import * as selectors from '../selectors';

import FindAccountResult from './FindAccountResult';


const FindAccount = () => {

    const dispatch = useDispatch();
    const reservation = useSelector(selectors.getReservation);

    useEffect(() => {
        
            dispatch(actions.findAccount(reservation.id));
       
    }, [reservation.id, dispatch]);

    

    return (
        <FindAccountResult/>
    )

}

export default FindAccount;