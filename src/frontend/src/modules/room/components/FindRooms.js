import React, { useState, useEffect  } from 'react';
import { useDispatch, useSelector } from "react-redux"
import FindRoomsResult from './FindRoomsResult';
import StatusSelector from './StatusSelector';

import * as actions from '../actions';

import hotel from "../../hotel";
import users from "../../users";


const FindRooms = () => {

    const dispatch = useDispatch();
    const [status, setStatus] = useState('');
    const hotels = useSelector(hotel.selectors.getHotels);
    const user = useSelector(users.selectors.getUser);

    const hotel1 = hotels.filter(hotel => hotel.address === user.address);
    const hotelid = hotel1[0].id;

    useEffect(() => {
        dispatch(actions.findRooms({hotelid:hotelid, status:status.trim(),type:"", page:0}));
    }, [hotelid,status, dispatch]);


    return (
    <div>
        <div className="formulario">
        <form className="form-inline mt-2 mt-md-0">

        <StatusSelector id='status' className='custom-select my-1 mr-sm-2' 
            value={status} onChange={e => setStatus(e.target.value)} />
        </form>
        </div>
        <br/>
        <FindRoomsResult/>
        </div>
    )

}

export default FindRooms;