import React from 'react';
import PropTypes from "prop-types";
import { FormattedMessage } from 'react-intl';

const Prices = ({prices}) => (


    <table className="table table-striped table-hover">

        <thead>
            <tr>
                <th scope="col">
                    <FormattedMessage id='project.global.fields.type'/>
                </th>
                <th scope="col">
                    <FormattedMessage id='project.global.fields.price'/>
                </th>
            </tr>
        </thead>
        
        <tbody>
            {prices && prices.map(price =>
                <tr key={price.id}>
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