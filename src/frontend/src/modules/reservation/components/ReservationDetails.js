import React, { useEffect } from 'react';
import { useDispatch, useSelector } from "react-redux";
import { Link, useParams, useHistory } from 'react-router-dom';
import * as selectors from "../selectors";
import * as actions from '../actions';
import users from '../../users';
import hotels from '../../hotel';

import { BackLink, ConfirmDialog } from '../../common';
import { FormattedMessage } from 'react-intl';



const ReservationDetails = () => {

    const dispatch = useDispatch();
    const history = useHistory();
    const hotelResult = useSelector(hotels.selectors.getHotels);
    const reservation = useSelector(selectors.getReservation);
    const { id } = useParams();
    const user = useSelector(users.selectors.getUser);

    let hotel1 = {...selectors.getReservations.filter(reservation => reservation.id === id)};

    if(user.role !== 'USER'){
        hotel1 = {...hotelResult.filter(hotel => hotel.address === user.address)};
    }
    

    const hotelid = hotel1[0].id;
    let adminValues = null;

    useEffect(() => {
        const reservationid = Number(id);

        if (!Number.isNaN(id)) {
            dispatch(actions.findReservationById(hotelid, reservationid));
        }
    }, [id,hotelid, dispatch]);

    const handleDelete = event => {
        event.preventDefault();

        //dispatch(actions.removeRoom(room));
        history.push('/rooms/find-rooms.result');
    }

    if (!reservation) {
        return null;
    }

    if (user.role === 'MANAGER') {
        adminValues = (
            <div className="navbar-nav">
                <Link className="nav-link" to={`/hotels/room-details/${room.id}/update`}>
                    <span className="fas fa-edit fa-2x"></span>
                </Link>
                <ConfirmDialog id='removeRoom' icon='eraser fa-3x' headerTitle='Remove Room'
                    bodyTitle='Are you sure that you want to remove it?' onConfirm={e => handleDelete(e)} />
            </div>
        )
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
                    <h6 className="card-subtitle text-muted">{room.roomtype.name}</h6>

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


export default ReservationDetails;