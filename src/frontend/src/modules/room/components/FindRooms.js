import React, { useState, useEffect  } from 'react';
import { FormattedMessage } from "react-intl";
import { useHistory } from "react-router-dom";
import { useDispatch, useSelector } from "react-redux"
import FindRoomsResult from './FindRoomsResult';

import * as actions from '../actions';

import hotel from "../../hotel";
import users from "../../users";


const FindRooms = () => {

    const dispatch = useDispatch();
    const history = useHistory();
    const [status, setStatus] = useState('');
    const hotels = useSelector(hotel.selectors.getHotels);
    const user = useSelector(users.selectors.getUser);

    const hotel1 = hotels.filter(hotel => hotel.address === user.address);
    const hotelid = hotel1[0].id;

    useEffect(() => {
        dispatch(actions.findRooms(hotelid, status.trim()));
    }, [hotelid,status, dispatch]);

    const handleSubmit = event => {
        event.preventDefault();

        dispatch(actions.findRooms(hotelid, status.trim()));
        history.push('/rooms/find-rooms-result');
    }

    return (
    <div>
        <div className="formulario">
        <form className="form-inline mt-2 mt-md-0" onSubmit={e => handleSubmit(e)}>

            <input id="status" type="text" className="form-control mr-sm-2" placeholder="LIBRE"
                value={status} onChange={e => setStatus(e.target.value)} />

            <button type="submit" className="btn btn-primary my-2 my-sm-0">
                <FormattedMessage id='project.global.buttons.search' />
            </button>
        </form>
        </div>
        <br/>
        <FindRoomsResult/>
        </div>
    )

}

export default FindRooms;