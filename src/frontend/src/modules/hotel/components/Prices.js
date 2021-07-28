import React from 'react';
import PropTypes from "prop-types";
import { FormattedMessage } from 'react-intl';
import PriceLink from './PriceLink';

const Prices = ({prices}) => (

    <table className="table table-striped table-hover">

        <thead>
            <tr>
                <th scope="col">
                    <FormattedMessage id='project.global.fields.type'/>
                </th>
                <th scope="col">
                    <FormattedMessage id='project.global.fields.hotelManager'/>
                </th>
            </tr>
        </thead>
        
        <tbody>
            {prices.map(price =>
                <tr key={price.id}>
                    <td><PriceLink id={price.id} name={price.id}/></td>
                    <td>{price.type.name}</td>
                    <td>{price.price}</td>
                </tr>
            )}
        </tbody>
    </table>
   
);

Prices.propTypes = {
    prices: PropTypes.array.isRequired
};

export default Prices;