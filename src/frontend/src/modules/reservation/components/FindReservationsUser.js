import React, { useEffect  } from 'react';
import { useDispatch, useSelector } from "react-redux"

import * as actions from '../actions';

import users from "../../users";
import FindReservationsResult from './FindReservationsResult';


const FindReservationsUser = () => {

    const dispatch = useDispatch();
    const userName = useSelector(users.selectors.getUser).firstName;

    useEffect(() => {
        
            dispatch(actions.findReservations("",userName,""));
       
    }, [userName, dispatch]);

    

    return (
        <FindReservationsResult/>
    )

}

export default FindReservationsUser;