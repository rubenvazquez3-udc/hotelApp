import React, { useState, useEffect } from 'react';
import { FormattedMessage } from "react-intl";
import { useHistory } from "react-router-dom";
import { useDispatch, useSelector } from "react-redux"
import FindGuestResult from './FindGuestResult';

import * as actions from '../actions';

import hotel from "../../hotel";
import users from "../../users";


const FindGuests = () => {

    const dispatch = useDispatch();
    const history = useHistory();
    const [username, setUsername] = useState('');
    const hotels = useSelector(hotel.selectors.getHotels);
    const user = useSelector(users.selectors.getUser);

    const hotel1 = hotels.hotelResult.items.filter(hotel => hotel.address === user.address);
    const hotelid = hotel1[0].id;

    useEffect(() => {
        dispatch(actions.findGuests({hotelid: hotelid, username: username.trim(), page:0}));
    }, [hotelid, username, dispatch]);

    const handleSubmit = event => {
        event.preventDefault();

        dispatch(actions.findGuests({hotelid: hotelid, username: username.trim(), page:0}));
        history.push('/guests');
    }

    return (
        <div>
            <div className="formulario">
                <form className="form-inline mt-2 mt-md-0" onSubmit={e => handleSubmit(e)}>

                    <input id="username" type="text" className="form-control mr-sm-2" placeholder='Name'
                        value={username} onChange={e => setUsername(e.target.value)} />

                    <button type="submit" className="btn btn-primary my-2 my-sm-0">
                        <FormattedMessage id='project.global.buttons.search' />
                    </button>
                </form>
            </div>
            <br />
            <FindGuestResult />
        </div>
    )

}

export default FindGuests;