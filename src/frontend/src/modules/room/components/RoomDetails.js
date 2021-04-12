import React, { useEffect } from 'react';
import { useDispatch, useSelector } from "react-redux";
import { Link, useParams } from 'react-router-dom';
import * as selectors from "../selectors";
import * as actions from '../actions';
import users from '../../users';
import hotels from '../../hotel';

import { BackLink } from '../../common';
import { FormattedMessage } from 'react-intl';



const RoomDetails = () => {

    const dispatch = useDispatch();
    const hotelResult = useSelector(hotels.selectors.getHotels);
    const room = useSelector(selectors.getRoom);
    const { id } = useParams();
    const user = useSelector(users.selectors.getUser);

    const hotel1 = hotelResult.filter(hotel => hotel.address === user.address);

    let adminValues = null;

    useEffect(() => {
        const roomid = Number(id);

        if (!Number.isNaN(id)) {
            dispatch(actions.findRoomById(hotel1[0].id, roomid));
        }
    }, [id,hotel1, dispatch]);

    if (!room) {
        return null;
    }

    if (user.role === 'MANAGER') {
        adminValues = (
            <div className="navbar-nav">
                <Link className="nav-link" to={`/hotels/room-details/${room.id}/update`}>
                    <FormattedMessage id="project.room.UpdateRoom.title" />
                </Link>
                <Link className="nav-link" to={`/hotels/room-details/${room.id}/remove`} >
                    <FormattedMessage id="project.room.RemoveRoom.title" />
                </Link>
            </div>
        )
    } else if (user.role === 'HOTEL') {
        adminValues =
            <Link className="nav-link" to={`/hotels/room-details/${room.id}/update`}>
                <FormattedMessage id="project.room.UpdateRoom.title" />
            </Link>
    }

    return (
        <div>
            <BackLink />

            <div className="card text-center">
                <div className="card-body">

                    <label htmlFor="number" className="col-md-3 col-form-label">
                        <FormattedMessage id="project.global.fields.roomNumber" />
                    </label>
                    <h5 className="card-title">{room.number}</h5>

                    <label htmlFor="type" className="col-md-3 col-form-label">
                        <FormattedMessage id="project.global.fields.type" />
                    </label>
                    <h6 className="card-subtitle text-muted">{room.type.name}</h6>

                    <label htmlFor="status" className="col-md-3 col-form-label">
                        <FormattedMessage id="project.global.fields.roomStatus" />
                    </label>
                    <p className="card-text"> {room.status}</p>
                </div>

                {adminValues}
            </div>

        </div>
    );
};


export default RoomDetails;