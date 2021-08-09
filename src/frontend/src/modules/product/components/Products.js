import React from 'react';
import PropTypes from "prop-types";
import { FormattedMessage } from 'react-intl';
import ProductLink from './ProductLink';

const Products = ({products}) => (

    <table className="table table-striped table-hover">

        <thead>
            <tr>
                <th scope="col">
                    <FormattedMessage id='project.global.fields.product'/>
                </th>
                <th scope="col">
                    <FormattedMessage id='project.global.fields.price'/>
                </th>
            </tr>
        </thead>
        
        <tbody>
            {products && products.map(service =>
                <tr key={service.id}>
                    <td><ProductLink id={service.id} name={service.name}/></td>
                    <td>{service.price}</td>
                </tr>
            )}
        </tbody>
    </table>
   
);

Products.propTypes = {
    products: PropTypes.array.isRequired
};

export default Products;