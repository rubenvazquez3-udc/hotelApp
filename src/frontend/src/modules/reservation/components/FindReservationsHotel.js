import React, { useState, useEffect  } from 'react';
import { FormattedMessage } from "react-intl";
import { useHistory } from "react-router-dom";
import { useDispatch, useSelector } from "react-redux"

import * as actions from '../actions';

import hotel from "../../hotel";
import users from "../../users";
import FindReservationsResult from './FindReservationsResult';


const FindReservationsHotel = () => {

    const dispatch = useDispatch();
    const history = useHistory();
    const [userName, setUserName] = useState('');
    const [date, setDate] = useState('');
    const hotels = useSelector(hotel.selectors.getHotels);
    const user = useSelector(users.selectors.getUser);

    
    const hotel1 = hotels.filter(hotel => hotel.address === user.address);
    const hotelid = hotel1[0].id;

    
    useEffect(() => {
            dispatch(actions.findReservations({hotelid:hotelid, username:userName, date:date, page:0}));
    }, [hotelid,userName,date, dispatch]);

    
    return (
        <div>
            <div className="formulario">
            <form className="form-inline mt-2 mt-md-0">
    
                <input id="username" type="text" className="form-control mr-sm-2"
                    value={userName} onChange={e => setUserName(e.target.value)} />
    
                <input id="date" type="date" className="form-control mr-sm-2"
                    value={date} onChange={e => setDate(e.target.value)} />
            </form>
            </div>
            <br/>
            <FindReservationsResult/>
            </div>
    )

}

export default FindReservationsHotel;