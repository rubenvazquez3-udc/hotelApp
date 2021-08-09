import React from 'react';
import { FormattedMessage } from "react-intl";
import { useDispatch, useSelector } from "react-redux"
import { Pager } from '../../common';

import * as selectors from '../selectors';
import * as actions from '../actions';
import Products from './Products';


const FindProductsResult = () => {

    const productSearch = useSelector(selectors.getProducts);
    const dispatch = useDispatch();

    if (!productSearch) {
        return null;
    }

    if (productSearch.length === 0) {
        return (
            <div className="alert alert-danger" role="alert" >
                <FormattedMessage id='project.service.FindServicesResult.noServicesFound' />
            </div>
        );
    }

    return (
        <div>
            <Products products={productSearch.products.items} />
            <Pager
                back={{
                    enabled: productSearch.criteria.page >=1,
                    onClick: () => dispatch(actions.previousFindProductsResultPage(productSearch.criteria))
                }}

                next= {{
                    enabled: productSearch.products.existMoreItems,
                    onClick: () => dispatch(actions.nextFindProductsResultPage(productSearch.criteria))
                }} />
        </div>

    );

}

export default FindProductsResult;