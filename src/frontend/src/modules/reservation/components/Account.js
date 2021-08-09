import React from 'react';
import PropTypes from "prop-types";
import { BackLink} from '../../common';
import { FormattedMessage, FormattedNumber } from 'react-intl';
import AccountItemList from './AccountItemList';



const Account = ({ account }) =>(
        
            <div className="card">
                <div className="card-header">
                    <BackLink /> <h5 className="card-title text-center"> {account.id}</h5>
                </div>
                <div className="card-body">

                    <p className="card-text text-right">
                        <FormattedMessage id='project.global.fields.totalPrice'/> <FormattedNumber value={account.totalPrice}/>
                    </p>
                
                    <AccountItemList list={account.items}/>
                </div>

                </div>
    );

Account.propTypes = {
        account: PropTypes.object.isRequired
    };

export default Account;