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

    //const price = useSelector(selectors.getPrice);

    const hotelid = hotel.id;

    let adminValues = null;

    const handleDelete = event => {
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
                <div className="card-body text-center">

                <BackLink /> 

                    <h6 className="card-text"><FormattedMessage id="project.global.fields.type" /> : {price.type.name}</h6>
                    <br/>
                    <p className="card-text">  <FormattedMessage id="project.global.fields.price" /> : {price.price}</p>
                </div>

                <div className="card-footer text-center">
                    {adminValues}
                </div>
                
            </div>

        </div>
    );
};


export default PriceDetails;