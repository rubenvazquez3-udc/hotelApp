import React, { useEffect  } from 'react';
import { useDispatch, useSelector } from "react-redux"

import * as actions from '../actions';
import * as selectors from '../selectors';

import FindAccountResult from './FindAccountResult';


const FindAccount = () => {

    const dispatch = useDispatch();
    const reservationid = useSelector(selectors.getReservation).id;

    useEffect(() => {
        
            dispatch(actions.findAccount(reservationid));
       
    }, [reservationid, dispatch]);

    

    return (
        <FindAccountResult/>
    )

}

export default FindAccount;