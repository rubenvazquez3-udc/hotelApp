import React, { useEffect } from 'react';
import { useDispatch, useSelector } from "react-redux";
import { Link, useParams, useHistory } from 'react-router-dom';
import * as selectors from "../selectors";
import * as actions from '../actions';
import users from '../../users';

import { BackLink, ConfirmDialog } from '../../common';
import { FormattedMessage } from 'react-intl';


//COMPROBAR QUE FUNCIONA

const PriceDetails = () => {

    const dispatch = useDispatch();
    const history = useHistory();
    const hotel = useSelector(selectors.getHotel);
    const { id } = useParams();
    const user = useSelector(users.selectors.getUser);

    const price = hotel.prices.filter(price => price.id === parseInt(id));

    const hotelid = hotel.id;

    let adminValues = null;

    const handleSubmit = event => {
        event.preventDefault();

        dispatch(actions.removeHotel(hotel,hotel.id,() => console.log(hotel.address), error => console.log(error)));
        history.push('/')
    }


    useEffect(() => {
        const priceid = Number(id);

        if (!Number.isNaN(id)) {
            dispatch(actions.findPriceById(hotelid, priceid));
        }
    }, [hotelid,id, dispatch]);

    if (!price) {
        return null;
    }

    if (user.role === 'MANAGER' && hotel.address === user.address) {
        adminValues = (
                        <div>
                            <Link className="nav-link" to={`/hotels/hotel-details/${price.id}/update`}>
                                <FormattedMessage id="project.hotels.UpdateHotel.title" />
                            </Link>
                            <Link className="nav-link" to={`/hotels/hotel-details/${hotel.id}/add-rooms`} >
                                <FormattedMessage id="project.hotels.AddRoom.title" />
                            </Link>
                        </div>
            
        )
    }

    return (
        <div>
            
            <div className="card">

                <div className="card-header">
                    <BackLink /> 
                </div>

                <div className="card-body text-center">

                    <h6 className="card-subtitle"><FormattedMessage id="project.global.fields.address" /> : {hotel.address}</h6>
                    <br/>
                    <p className="card-text">  <FormattedMessage id="project.global.fields.hotelManager" /> : {hotel.manager}</p>

                    <p className="card-text"> <FormattedMessage id="project.global.fields.description" /> : {hotel.description}</p>

                    <p className="card-text"> <span className="fas fa-phone-square"/>  {hotel.phoneNumber}</p>
                </div>

                <div className="card-footer text-center">
                    {adminValues}
                </div>
                
            </div>

        </div>
    );
};


export default PriceDetails;