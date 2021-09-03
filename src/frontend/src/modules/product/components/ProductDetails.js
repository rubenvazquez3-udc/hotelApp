import React, { useEffect } from 'react';
import { useDispatch, useSelector } from "react-redux";
import { Link, useParams, useHistory } from 'react-router-dom';
import * as selectors from "../selectors";
import * as actions from '../actions';
import users from '../../users';

import { BackLink, ConfirmDialog } from '../../common';
import { FormattedMessage } from 'react-intl';
import {AddToAccount} from '../../reservation';


const ProductDetails = () => {

    const dispatch = useDispatch();
    const history = useHistory();
    const products = useSelector(selectors.getProducts);
    const product = useSelector(selectors.getProduct);
    const { id } = useParams();
    const user = useSelector(users.selectors.getUser);
    const reservation = useSelector(selectors.getReservation);

    let adminValues = null;

    const hotelid = products.products.items && products.products.items.filter(product => product.id === parseInt(id))[0].hotel.id;

    const loggedAsUser = user.role === 'USER';

    useEffect(() => {
        const productid = Number(id);
        const today = new Date().toJSON().split('T')[0];
        const userid = user.id;

        if (!Number.isNaN(id)) {
            dispatch(actions.findProductById(hotelid,productid));
        }
        if(loggedAsUser) {
            dispatch(actions.findReservationsByUserAndDate(hotelid, userid, today));
        }
    }, [id,hotelid,user.id,loggedAsUser, dispatch]);

    const handleDelete = event => {
        event.preventDefault();

        dispatch(actions.removeProduct(product,
            () => history.push(`/hotels/hotel-details/${product.hotel.id}`),
            error => console.log(error)));

    }

    if (!product) {
        return null;
    }

    if (user.role === 'MANAGER' && product.hotel.address === user.address) {
        adminValues = (

                    <ul style={{display:"flex", justifyContent:'center'}}>
                        <li style={{display:"flex"}}>
                            <Link className="nav-link" to={`/products/details/${product.id}/update`}>
                                <span className="fas fa-edit fa-2x" title='Edit'/>
                            </Link>
                        </li>
                        <li style={{display:"flex"}}>
                            <ConfirmDialog id='removeProduct' icon='eraser fa-3x' headerTitle='Remove Product'
                                bodyTitle='Are you sure that you want to remove it?' onConfirm={e => handleDelete(e)} />
                        </li>
                    </ul>
        )
    } else if (user.role === 'HOTEL' && product.hotel.address === user.address) {
        adminValues =
                    <Link className="nav-link" to={`/products/details/${product.id}/update`}>
                        <FormattedMessage id="project.hotels.UpdateService.title" />
                    </Link>            
    }

    return (
        <div>
            
            <div className="card">

                <div className="card-header">
                    <BackLink />  <h5 className="card-title text-center">{product.name}</h5>
                </div>

                <div className="card-body text-center">

                    <h6 className="card-subtitle"><FormattedMessage id="project.global.fields.price" /> : {product.price}€</h6>
                    <br/>
                    <p className="card-text"> <FormattedMessage id="project.global.fields.description" /> : {product.description}</p>

                    { loggedAsUser && reservation && product &&
                        <div>
                            <br/>

                            <AddToAccount productId={product.id} serviceId={null} reservationId={reservation[0].id}/>
                        
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


export default ProductDetails;
