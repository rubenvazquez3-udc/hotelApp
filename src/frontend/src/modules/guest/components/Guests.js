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
                <th scope="col">
                    <FormattedMessage id='project.global.fields.date.in' />
                </th>
                <th scope="col">
                    <FormattedMessage id='project.global.fields.date.out' />
                </th>
            </tr>
        </thead>

        <tbody>
            {guests && guests.map(guest =>
                <tr key={guest.id}>
                    <td>{guest.guest.name}</td>
                    <td>{guest.guest.surname}</td>
                    <td>{guest.guest.dni}</td>
                    <td>{guest.guest.address}</td>
                    <td>{guest.guest.phoneNumber}</td>
                    <td>{guest.reservation.inbound}</td>
                    <td>{guest.reservation.outbound}</td>
                </tr>
            )}
        </tbody>

    </table>

);

Guests.propTypes = {
    guests: PropTypes.array.isRequired
};

export default Guests;