import React, { useEffect } from 'react';
import { useDispatch, useSelector } from "react-redux";
import { Link, useParams, useHistory } from 'react-router-dom';
import * as selectors from "../selectors";
import * as actions from '../actions';
import users from '../../users';

import { BackLink, ConfirmDialog } from '../../common';
import { FormattedMessage } from 'react-intl';


const PriceDetails = () => {

    const dispatch = useDispatch();
    const history = useHistory();
    const hotel = useSelector(selectors.getHotel);
    const { id } = useParams();
    const user = useSelector(users.selectors.getUser);

    const price = hotel.prices.filter(price => price.id === parseInt(id))[0];

    const hotelid = hotel.id;
    console.log(price);
    let adminValues = null;

    const handleDelete = event => {
        event.preventDefault();

        dispatch(actions.removePrice(hotelid,price,() => history.push(`/hotels/hotel-details/${hotelid}`), error => console.log(error)));

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

                    <ul style={{display:"flex", justifyContent:'center'}}>
                        <li  style={{display:"flex"}}>
                            <Link className="nav-link" to={`/prices/details/${price.id}/update`} >
                                <span className="fas fa-edit fa-2x" title='Edit'/>
                            </Link>
                        </li>
                        <li  style={{display:"flex"}}>
                            <ConfirmDialog id='removePrice' icon='eraser fa-3x' headerTitle='Remove Price'
                                           bodyTitle='Are you sure that you want to remove it?' onConfirm={e => handleDelete(e)} />
                        </li>
                    </ul>

        )
    }

    return (
        <div>
            
            <div className="card">
                <div className="card-body">

                <BackLink /> 

                    <h6 className="card-text text-center"><FormattedMessage id="project.global.fields.type" /> : {price.type.name}</h6>
                    <br/>
                    <p className="card-text text-center">  <FormattedMessage id="project.global.fields.price" /> : {price.price}</p>
                </div>

                <div className="card-footer text-center">
                    {adminValues}
                </div>
                
            </div>

        </div>
    );
};


export default PriceDetails;