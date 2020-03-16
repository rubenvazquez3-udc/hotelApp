import React from 'react';
import {useSelector} from 'react-redux';
import {FormattedMessage} from 'react-intl';

import * as selectors from '../selectors';
import OrderLink from './OrderLink';

const PurchaseCompleted = () => {

    const orderId = useSelector(selectors.getLastOrderId);

    if (!orderId) {
        return null;
    }
    
    return (
        <div className="alert alert-success" role="alert">
            <FormattedMessage id="project.shopping.PurchaseCompleted.purchaseOrderGenerated"/>:
            &nbsp;
            <OrderLink id={orderId}/>
        </div>
    );

}

export default PurchaseCompleted;