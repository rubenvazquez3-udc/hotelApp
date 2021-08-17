import React, { useEffect } from 'react';
import { useDispatch, useSelector } from "react-redux";
import { Link, useParams, useHistory } from 'react-router-dom';
import * as selectors from "../selectors";
import * as actions from '../actions';
import users from '../../users';
import hotels from '../../hotel';

import { BackLink, ConfirmDialog } from '../../common';
import { FormattedMessage } from 'react-intl';



const RoomDetails = () => {

    const dispatch = useDispatch();
    const history = useHistory();
    const hotelResult = useSelector(hotels.selectors.getHotels);
    const room = useSelector(selectors.getRoom);
    const { id } = useParams();
    const user = useSelector(users.selectors.getUser);

    const hotel1 = {...hotelResult.hotelResult.items.filter(hotel => hotel.address === user.address)};

    const hotelid = hotel1[0].id;
    let adminValues = null;

    useEffect(() => {
        const roomid = Number(id);

        if (!Number.isNaN(id)) {
            dispatch(actions.findRoomById(hotelid, roomid));
        }
    }, [id,hotelid, dispatch]);

    const handleDelete = event => {
        event.preventDefault();

        dispatch(actions.removeRoom(room,() => history.push('/rooms/find-rooms-result'), error => console.log(error)));
        //history.push('/rooms/find-rooms-result');
    }

    if (!room) {
        return null;
    }

    if (user.role === 'MANAGER') {
        adminValues = (
            <div className="navbar-nav">
                <Link className="nav-link" to={`/hotels/room-details/${room.id}/update`}>
                    <span className="fas fa-edit fa-2x"/>
                </Link>
                <ConfirmDialog id='removeRoom' icon='eraser fa-3x' headerTitle='Remove Room'
                    bodyTitle='Are you sure that you want to remove it?' onConfirm={e => handleDelete(e)} />
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

            <div className="card">
                <div className="card-header">
                    <BackLink />  <h5 className="card-title text-center"> <FormattedMessage id="project.global.fields.roomNumber" /> {room.number}</h5>
                </div>

                <div className="card-body text-center">                   

                    <h6 className="card-text">  <FormattedMessage id="project.global.fields.type" /> : {room.type.name}</h6>
                    <p className="card-text">  <FormattedMessage id="project.global.fields.roomStatus" /> : {room.status}</p>
                </div>

                <div className="card-footer text-center">
                    {adminValues}
                </div>
            </div>

        </div>
    );
};


export default RoomDetails;