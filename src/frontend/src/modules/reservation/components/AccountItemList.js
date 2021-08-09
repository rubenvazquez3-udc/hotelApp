import React from 'react';
import PropTypes from "prop-types";
import { FormattedMessage, FormattedNumber } from 'react-intl';

const AccountItemList = ({ list }) => (

    <table className="table table-striped table-hover">

        <thead>
            <tr>
                <th scope="col" style = {{width:'50%'}}>
                    <FormattedMessage id='project.global.fields.item' />
                </th>
                <th scope="col" style={{width:'15%'}}>
                    <FormattedMessage id='project.global.fields.shopping.quantity' />
                </th>
                <th scope="col" style={{width:'15%'}}>
                    <FormattedMessage id='project.global.fields.price' />
                </th>
                <th scope="col" style={{width:'20%'}}>
                    <FormattedMessage id='project.global.fields.TotalPrice' />
                </th>

            </tr>
        </thead>

        <tbody>
            {list && list.map(item =>
                <tr key={item.id}>
                    <td>{item.name}</td>
                    <td>{item.quantity}</td>
                    <td><FormattedNumber value={item.price}/></td>
                    <td><FormattedNumber value={item.quantity*item.price}/></td>
                </tr>
            )}
        </tbody>

    </table>

);

AccountItemList.propTypes = {
    list: PropTypes.array.isRequired
};

export default AccountItemList;