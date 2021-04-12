import React, { useState } from 'react';
import { FormattedMessage } from "react-intl";
import { useHistory } from "react-router-dom";
import { useDispatch, useSelector } from "react-redux"

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

    const handleSubmit = event => {
        event.preventDefault();

        dispatch(actions.findRooms(hotel1[0].id, status.trim()));
        history.push('/rooms/find-rooms-result');
    }

    return (
        <form className="form-inline mt-2 mt-md-0" onSubmit={e => handleSubmit(e)}>

            <input id="status" type="text" className="form-control mr-sm-2" placeholder="LIBRE"
                value={status} onChange={e => setStatus(e.target.value)} />

            <button type="submit" className="btn btn-primary my-2 my-sm-0">
                <FormattedMessage id='project.global.buttons.search' />
            </button>
        </form>
    )

}

export default FindRooms;