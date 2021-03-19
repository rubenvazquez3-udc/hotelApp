import React, { useEffect } from 'react';
import {useDispatch, useSelector} from "react-redux";
import { Link, useParams } from 'react-router-dom';
import * as selectors from "../selectors";
import * as actions from '../actions';
import users from '../../users';

import {BackLink} from '../../common';
import { FormattedMessage } from 'react-intl';
import UpdateHotel from '../components/UpdateHotel';

const HotelDetails = () => {

    const dispatch = useDispatch();
    const hotel = useSelector(selectors.getHotel);
    const {id} = useParams();
    const user = useSelector(users.selectors.getUser);

    let adminValues = null;

    const updateHotel =  <UpdateHotel/>;

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
        <button className="nav-link" onClick ={updateHotel}>
            <FormattedMessage id="project.hotels.UpdateHotel.title" />
        </button>
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
                    <label htmlFor="name" className="col-md-3 col-form-label">
                        <FormattedMessage id="project.global.fields.hotelName"/>
                    </label>
                    <h5 className="card-title">{hotel.name}</h5>
                    <label htmlFor="address" className="col-md-3 col-form-label">
                        <FormattedMessage id="project.global.fields.address"/>
                    </label>
                    <h6 className="card-subtitle text-muted">{hotel.address}</h6>

                    <label htmlFor="manager" className="col-md-3 col-form-label">
                                <FormattedMessage id="project.global.fields.hotelManager"/>
                            </label>
                    <p className="card-text">{hotel.manager}</p>
                    
                    <label htmlFor="description" className="col-md-3 col-form-label">
                        <FormattedMessage id="project.global.fields.description"/>
                    </label>
                    <p className="card-text"> {hotel.description}</p>

                    <label htmlFor="phoneNumber" className="col-md-3 col-form-label">
                        <FormattedMessage id="project.global.fields.phone"/>
                        <span className="fas fa-phone-square" />
                    </label>
                    <p className="card-text"> {hotel.phoneNumbre}</p>
                </div>

                {adminValues}
            </div>
            
        </div>
    );
};


export default HotelDetails;