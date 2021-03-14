import React, { useEffect } from 'react';
import {useDispatch, useSelector} from "react-redux";
import { Link, useParams } from 'react-router-dom';
import * as selectors from "../selectors";
import * as actions from '../actions';
import users from '../../users';

import {BackLink} from '../../common';
import { FormattedMessage } from 'react-intl';



const HotelDetails = () => {

    const dispatch = useDispatch();
    const hotel = useSelector(selectors.getHotel);
    const {id} = useParams();
    const user = useSelector(users.selectors.getUser);

    let adminValues = null;

    useEffect(()=>{
        const hotelid = Number(id);

        if (!Number.isNaN(id)){
            dispatch(actions.findHotelById(hotelid));
        }
    }, [id, dispatch]);

    if (!hotel){
        return null;
    }

    if(user.role === 'ADMIN'){
        adminValues =( 
        <div className="navbat-nav">
        <Link className="nav-link" to={`/hotels/hotel-details/${hotel.id}/update`}>
            <FormattedMessage id="project.hotels.UpdateHotel.title" />
        </Link>
        <Link className="nav-link" to={`/hotels/hotel-details/${hotel.id}/remove`} >
            <FormattedMessage id="project.hotels.RemoveHotel.title"/>
        </Link>
    </div>
    )
    } else if (user.role === 'MANAGER' && hotel.address === user.address) {
        adminValues = ( 
        <div className="navbat-nav">
        <Link className="nav-link" to={`/hotels/hotel-details/${hotel.id}/update`}>
            <FormattedMessage id="project.hotels.UpdateHotel.title" />
        </Link>
        <Link className="nav-link" to={`/hotels/hotel-details/${hotel.id}/add-rooms`} >
            <FormattedMessage id="project.hotels.AddRoom.title"/>
        </Link>
    </div>
    )
    } else if (user.role === 'USER'){
        adminValues = 
        <Link className="nav-link" to={`/hotels/hotel-details/${hotel.id}/reservations`} >
            <FormattedMessage id="project.hotels.AddReservation.title"/>
        </Link>   
    }

    return (
        <div>
            <BackLink/>

            <div className="card text-center">
                <div className="card-body">
                    <h5 className="card-title">{hotel.name}</h5>
                    <h6 className="card-subtitle text-muted">{hotel.address}</h6>
                    <p className="card-text"> {hotel.description}</p>
                    <p className="card-text"> {hotel.phoneNumbre}</p>
                </div>

                {adminValues}
            </div>
            
        </div>
    );
};


export default HotelDetails;