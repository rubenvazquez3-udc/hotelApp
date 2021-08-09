import React from 'react';
import { FormattedMessage } from "react-intl";
import { useSelector } from "react-redux"

import * as selectors from '../selectors';
import Account from './Account';


const FindAccountResult = () => {

    const account = useSelector(selectors.getAccount);

    if (!account) {
        return null;
    }

    if (account.items.length === 0) {
        return (
            <div className="alert alert-warning" role="alert" >
                <FormattedMessage id='project.reservations.FindAccountResult.noItemsFound' />
            </div>
        );
    }

    return (
        <div>
            <Account account={account} />
        </div>

    );

}

export default FindAccountResult;