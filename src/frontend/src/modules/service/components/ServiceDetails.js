import React, { useEffect } from 'react';
import { useDispatch, useSelector } from "react-redux";
import { Link, useParams, useHistory } from 'react-router-dom';
import * as selectors from "../selectors";
import * as actions from '../actions';
import users from '../../users';

import { BackLink, ConfirmDialog } from '../../common';
import { FormattedMessage } from 'react-intl';
import AddToAccount from './AddToAccount';


const ServiceDetails = () => {

    const dispatch = useDispatch();
    const history = useHistory();
    const services = useSelector(selectors.getServices);
    const service = useSelector(selectors.getService);
    const { id } = useParams();
    const user = useSelector(users.selectors.getUser);
    const reservation = useSelector(selectors.getReservation);

    let adminValues = null;

    const hotelid = {... services.filter(service => service.id === parseInt(id))}.id;

    const handleDelete = event => {
        event.preventDefault();

        dispatch(actions.removeService(service));
        //history.push('/')
    }

    const today = new Date().toJSON().split('T')[0];
    const userid = user.id;

    const loggedAsUser = user.role === 'USER';

    useEffect(() => {
        const serviceid = Number(id);

        if (!Number.isNaN(id)) {
            dispatch(actions.findServiceById(hotelid,serviceid));
        }
            dispatch(actions.findReservationsByUserAndDate(hotelid,userid,today));
    }, [id,hotelid,userid,today, dispatch]);

    if (!service) {
        return null;
    }

    if (user.role === 'HOTEL' && service.hotel.address === user.address) {
        adminValues = (
                <div className="form-group row">
                    <ul id='admin'>
                        <li id='managerbutton'>
                            <Link className="nav-link" to={`/services/details/${service.id}/update`}>
                                <span className="fas fa-edit fa-2x"></span>
                            </Link>
                        </li>
                        <li id='managerbutton'>
                            <ConfirmDialog id='removeService' icon='eraser fa-3x' headerTitle='Remove Service'
                                bodyTitle='Are you sure that you want to remove it?' onConfirm={e => handleDelete(e)} />
                        </li>
                    </ul>
                </div>
        )
    } else if (user.role === 'HOTEL' && service.hotel.address === user.address) {
        adminValues =
                    <Link className="nav-link" to={`/services/details/${service.id}/update`}>
                        <FormattedMessage id="project.hotels.UpdateHotel.title" />
                    </Link>            
    }

    return (
        <div>
            
            <div className="card">

                <div className="card-header">
                    <BackLink />  <h5 className="card-title text-center">{service.name}</h5>
                </div>

                <div className="card-body text-center">

                    <h6 className="card-subtitle"><FormattedMessage id="project.global.fields.price" /> : {service.price}€</h6>
                    <br/>
                    <p className="card-text"> <FormattedMessage id="project.global.fields.description" /> : {service.description}</p>

                    { loggedAsUser &&
                        <div>
                            <br/>

                            <AddToAccount productId={null} serviceId={service.id} reservationId={reservation.id}/>
                        
                        </div>

                    }
                </div>

                <div className="card-footer text-center">
                    {adminValues}
                </div>
                
            </div>

        </div>
    );
};


export default ServiceDetails;
