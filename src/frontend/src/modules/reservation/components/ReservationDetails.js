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
    const reservations = useSelector(selectors.getReservations);
    const hotelResult = useSelector(hotels.selectors.getHotels);
    const reservation = useSelector(selectors.getReservation);
    const { id } = useParams();
    const user = useSelector(users.selectors.getUser);

    let hotel1 = {...reservations.reservations.items.filter(reservation => reservation.id === parseInt(id))};
    let hotelid = hotel1[0].hotel.id;

    if(user.role !== 'USER'){
        hotel1 = {...hotelResult.hotelResult.items.filter(hotel => hotel.address === user.address)};
        hotelid = hotel1[0].id;
    }
    

  

    useEffect(() => {
        const reservationid = Number(id);

        if (!Number.isNaN(id)) {
            dispatch(actions.findReservationById(reservationid));
        }
    }, [id,hotelid, dispatch]);

    const handleDelete = event => {
        event.preventDefault();

       dispatch(actions.removeReservation(reservation));
        history.push('/reservations');
    }

    if (!reservation) {
        return null;
    }

    let values = null;

    if(user.role !== 'USER'){
        values = 
            <div style={{display:"flex", justifyContent:'center'}}>
                <Link className='nav-link' to={`/reservations/reservation-details/${reservation.id}/assignRoom`}>
                    <span className='fas fa-bed fa-2x' title='Assign Room'/>
                </Link>
                <Link className='nav-link' to={`/reservations/reservation-details/${reservation.id}/addGuest`}>
                    <span className='fas fa-user-plus fa-2x' title='Add Guest'/>
                </Link>
            </div>
    }

    console.log(user);
    return (
        <div>

            <div className="card">
                <div className="card-header">
                    <BackLink /> <h5 className="card-title text-center"> {reservation.id}</h5>
                </div>
                <div className="card-body text-center">

                    <p className="card-text"><FormattedMessage id="project.global.fields.type" /> : {reservation.roomtype.name}</p>

                    <p className="card-text"> <FormattedMessage id="project.global.fields.date.in" />: {reservation.inbound}</p>

                    <p className="card-text"> <FormattedMessage id="project.global.fields.date.out" />: {reservation.outbound}</p>

                    <p className="card-text"> <FormattedMessage id="project.global.fields.firstName"/>: {reservation.user.firstName}</p>

                    <p className="card-text"><FormattedMessage id="project.global.fields.hotelName"/>: {reservation.hotel.name}</p>

                    <p className="card-text"> <FormattedMessage id="project.global.fields.quantity"/>: {reservation.rooms}</p>
                </div>

                <div className="card-footer text-center">
                <div style={{display:"flex", justifyContent:'center'}}>
                <Link className="nav-link" to={`/reservations/reservation-details/${reservation.id}/update`}>
                    <span className="fas fa-edit fa-2x" title='Edit'/>
                </Link>
                <Link className="nav-link" to={`/reservations/reservation-details/${reservation.id}/account`}  style={{display:"flex"}}>
                    <span className="fas fa-file-alt fa-2x" title='Account'/>
                </Link>
                <ConfirmDialog title='Remove' id='removeReservation' icon='eraser fa-3x' headerTitle='Remove Reservation'
                    bodyTitle='Are you sure that you want to remove it?' onConfirm={e => handleDelete(e)} />
                </div>
                    {values}
                </div>
                    
                </div>
            </div>
    );
};


export default ReservationDetails;