import React from 'react';
import PropTypes from "prop-types";
import { FormattedMessage } from 'react-intl';
import { ReservationLink } from '../../common';

const Reservations = ({ reservations }) => (

    <table className="table table-striped table-hover">

        <thead>
            <tr>
                <th scope="col">
                    <FormattedMessage id='project.global.fields.id' />
                </th>
                <th scope="col">
                    <FormattedMessage id='project.global.fields.type' />
                </th>
                <th scope="col">
                    <FormattedMessage id='project.global.fields.hotelName' />
                </th>
                <th scope="col">
                    <FormattedMessage id='project.global.fields.firstName' />
                </th>
                <th scope="col">
                    <FormattedMessage id='project.global.fields.date.in' />
                </th>
                <th scope="col">
                    <FormattedMessage id='project.global.fields.date.out' />
                </th>
                <th scope="col">
                    <FormattedMessage id='project.global.fields.quantity' />
                </th>
            </tr>
        </thead>

        <tbody>
            {reservations && reservations.map(reservation =>
                <tr key={reservation.id}>
                    <td><ReservationLink id={reservation.id} name={reservation.id}/></td>
                    <td>{reservation.roomtype.name}</td>
                    <td>{reservation.hotel.name}</td>
                    <td>{reservation.user.firstName}</td>
                    <td>{reservation.inbound}</td>
                    <td>{reservation.outbound}</td>
                    <td>{reservation.rooms}</td>
                </tr>
            )}
        </tbody>

    </table>

);

Reservations.propTypes = {
    reservations: PropTypes.array.isRequired
};

export default Reservations;