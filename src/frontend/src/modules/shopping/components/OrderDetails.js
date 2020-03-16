import React, {useEffect} from 'react';
import {useSelector, useDispatch} from 'react-redux';
import {FormattedMessage, FormattedDate, FormattedTime} from 'react-intl';
import {useParams} from 'react-router-dom';

import * as actions from '../actions';
import * as selectors from '../selectors';
import ShoppingItemList from './ShoppingItemList';
import {BackLink} from '../../common';

const OrderDetails = () => {

    const {id} = useParams();
    const order = useSelector(selectors.getOrder);
    const dispatch = useDispatch();

    useEffect(() => {

        if (!Number.isNaN(id)) {   
            dispatch(actions.findOrder(id));
        }

        return () => dispatch(actions.clearOrder());

    }, [id, dispatch]);

    if (!order) {
        return null;
    }

    return (

        <div>

            <BackLink/>

            <div className="card text-center">
                <div className="card-body">
                    <h5 className="card-title">
                        <FormattedMessage id='project.global.fields.purchaseOrder'/> {order.id} 
                    </h5>
                    <h6 className="card-subtitle text-muted">
                        <FormattedDate value={new Date(order.date)}/> - <FormattedTime value={new Date(order.date)}/>
                    </h6>
                    <p className="card-text">
                        {order.postalAddress} - {order.postalCode} 
                    </p>
                </div>
            </div>

            <ShoppingItemList list={order}/>

        </div>

    );

}

export default OrderDetails;