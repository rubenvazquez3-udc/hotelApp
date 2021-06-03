import React from 'react';
import PropTypes from "prop-types";
import { FormattedMessage } from 'react-intl';

const Guests = ({ guests }) => (

    <table className="table table-striped table-hover">

        <thead>
            <tr>
                <th scope="col">
                    <FormattedMessage id='project.global.fields.firstName' />
                </th>
                <th scope="col">
                    <FormattedMessage id='project.global.fields.lastName' />
                </th>
                <th scope="col">
                    <FormattedMessage id='project.global.fields.id' />
                </th>
                <th scope="col">
                    <FormattedMessage id='project.global.fields.address' />
                </th>
                <th scope="col">
                    <FormattedMessage id='project.global.fields.phone' />
                </th>
            </tr>
        </thead>

        <tbody>
            {guests.map(guest =>
                <tr key={guest.id}>
                    <td>{guest.guest.name}</td>
                    <td>{guest.guest.surname}</td>
                    <td>{guest.guest.dni}</td>
                    <td>{guest.guest.address}</td>
                    <td>{guest.guest.phoneNumber}</td>
                </tr>
            )}
        </tbody>

    </table>

);

Guests.propTypes = {
    guests: PropTypes.array.isRequired
};

export default Guests;