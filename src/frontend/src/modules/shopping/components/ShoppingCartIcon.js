import React from 'react';
import {useSelector} from 'react-redux';

import * as selectors from '../selectors';

const ShoppingCartIcon = () => {

    const shoppingCart = useSelector(selectors.getShoppingCart);
    
    return (
        <div>
            <span className="fas fa-shopping-cart"></span>&nbsp;
            ({shoppingCart.totalQuantity})
        </div>
    );

}

export default ShoppingCartIcon;
