import React from 'react';
import { FormattedMessage } from "react-intl";
import { useDispatch, useSelector } from "react-redux"
import { Pager } from '../../common';

import * as selectors from '../selectors';
import * as actions from '../actions';
import Services from './Services';


const FindServicesResult = () => {

    const serviceSearch = useSelector(selectors.getServices);
    const dispatch = useDispatch();

    if (!serviceSearch) {
        return null;
    }

    if (serviceSearch.services.items.length === 0) {
        return (
            <div className="alert alert-danger" role="alert" >
                <FormattedMessage id='project.service.FindServicesResult.noServicesFound' />
            </div>
        );
    }

    return (
        <div>
            <Services services={serviceSearch.services.items} />
            <Pager
                back={{
                    enabled: serviceSearch.criteria.page >=1,
                    onClick: () => dispatch(actions.previousFindServicesResultPage(serviceSearch.criteria))
                }}

                next= {{
                    enabled: serviceSearch.services.existMoreItems,
                    onClick: () => dispatch(actions.nextFindServicesResultPage(serviceSearch.criteria))
                }} />
        </div>

    );

}

export default FindServicesResult;