import React, { useEffect } from 'react';
import {useDispatch, useSelector} from "react-redux";
import { Link, useParams, useHistory } from 'react-router-dom';
import * as selectors from "../selectors";
import * as actions from '../actions';
import users from '../../users';

import {BackLink, ConfirmDialog} from '../../common';
import { FormattedMessage } from 'react-intl';


const HotelDetails = () => {

    const dispatch = useDispatch();
    const history = useHistory();
    const hotel = useSelector(selectors.getHotel);
    const {id} = useParams();
    const user = useSelector(users.selectors.getUser);

    let adminValues = null;

    const handleSubmit = event => {
        event.preventDefault();

         dispatch(actions.removeHotel(hotel));
         history.push('/')
    }


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
        <ul className="navbar-nav">
            <li className="nav-item">
        <Link className="nav-link" to={`/hotels/hotel-details/${hotel.id}/update`}>
            <FormattedMessage id="project.hotels.UpdateHotel.title" />
        </Link>
        </li>
        <li className="nav-item">
        <ConfirmDialog id='removeHotel' icon='eraser' headerTitle='Remove Hotel' 
        bodyTitle='Are you sure that you want to remove it?' onConfirm={e => handleSubmit(e)} />
        </li>
    </ul>
    )
    } else if (user.role === 'MANAGER' && hotel.address === user.address) {
        adminValues = ( 
        <ul className="navbat-nav">
            <li className="nav-item">
        <Link className="nav-link" to={`/hotels/hotel-details/${hotel.id}/update`}>
            <FormattedMessage id="project.hotels.UpdateHotel.title" />
        </Link>
        </li>
        <li className="nav-item">
        <Link className="nav-link" to={`/hotels/hotel-details/${hotel.id}/add-rooms`} >
            <FormattedMessage id="project.hotels.AddRoom.title"/>
        </Link>
        </li>
    </ul>
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
                    <span className="fas fa-phone-square" />
                    <p className="card-text"> {hotel.phoneNumbre}</p>
                </div>

                {adminValues}
            </div>
            
        </div>
    );
};


export default HotelDetails;