import React from 'react';
import PropTypes from "prop-types";
import { FormattedMessage } from 'react-intl';
import HotelLink from '../../common/components/HotelLink';

const Hotels = ({hotels}) => (

    <table className="table table-striped table-hover">

        <thead>
            <tr>
                <th scope="col">
                    <FormattedMessage id='project.global.fields.hotelName'/>
                </th>
                <th scope="col">
                    <FormattedMessage id='project.global.fields.hotelManager'/>
                </th>
                <th scope="col">
                    <FormattedMessage id='project.global.fields.address'/>
                </th>
                <th scope='col'>
                    <FormattedMessage id='project.global.fields.phone'/>
                </th>
            </tr>
        </thead>
        
        <tbody>
            {hotels.map(hotel =>
                <tr key={hotel.id}>
                    <td><HotelLink id={hotel.id} name={hotel.name}/></td>
                    <td>{hotel.manager}</td>
                    <td>{hotel.address}</td>
                    <td>{hotel.phoneNumber}</td>
                </tr>
            )}
        </tbody>

    </table>
   
);

Hotels.propTypes = {
    hotels: PropTypes.array.isRequired
};

export default Hotels;