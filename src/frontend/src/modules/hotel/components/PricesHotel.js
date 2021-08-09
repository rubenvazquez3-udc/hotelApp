import React from 'react';
import PropTypes from "prop-types";
import { FormattedMessage } from 'react-intl';
import PriceLink from './PriceLink';

const PricesHotel = ({prices}) => (


    <table className="table table-striped table-hover">

        <thead>
            <tr>
                <th scope='col'/>
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
                    <td><PriceLink id={price.id} name={price.id}/></td>
                    <td>{price.type.name}</td>
                    <td>{price.price}</td>
                </tr>
            )}
        </tbody>
    </table>
   
);

PricesHotel.propTypes = {
    prices: PropTypes.array.isRequired
};

export default PricesHotel;