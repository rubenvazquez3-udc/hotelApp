import React from 'react';
import PropTypes from "prop-types";
import { FormattedMessage } from 'react-intl';
import ServiceLink from './ServiceLink';

const Services = ({services}) => (

    <table className="table table-striped table-hover">

        <thead>
            <tr>
                <th scope="col">
                    <FormattedMessage id='project.global.fields.service'/>
                </th>
                <th scope="col">
                    <FormattedMessage id='project.global.fields.price'/>
                </th>
            </tr>
        </thead>
        
        <tbody>
            {services && services.map(service =>
                <tr key={service.id}>
                    <td><ServiceLink id={service.id} name={service.name}/></td>
                    <td>{service.price}</td>
                </tr>
            )}
        </tbody>
    </table>
   
);

Services.propTypes = {
    services: PropTypes.array.isRequired
};

export default Services;