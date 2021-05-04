import React from 'react';
import PropTypes from "prop-types";
import { FormattedMessage } from 'react-intl';
import { ReservationLink } from '../../common';

const Reservations = ({ reservations }) => (

    <table className="table table-striped table-hover">

        <thead>
            <tr>
                <th scope="col">
                    <FormattedMessage id='project.global.fields.roomNumber' />
                </th>
                <th scope="col">
                    <FormattedMessage id='project.global.fields.type' />
                </th>
                <th scope="col">
                    <FormattedMessage id='project.global.fields.roomStatus' />
                </th>
            </tr>
        </thead>

        <tbody>
            {reservations.map(reservation =>
                <tr key={reservation.id}>
                    <td><ReservationLink id={reservation.id} name={reservation.id}/></td>
                    <td>{reservation.type.name}</td>
                    <td>{reservation.hotel.name}</td>
                    <td>{reservation.user.firstName}</td>
                    <td>{reservation.inbound}</td>
                    <td>{reservation.outbound}</td>
                </tr>
            )}
        </tbody>

    </table>

);

Reservations.propTypes = {
    reservations: PropTypes.array.isRequired
};

export default Reservations;