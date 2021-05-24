import React from "react";
import PropTypes from "prop-types";

import {Link} from 'react-router-dom';


const ReservationLink = ({id, name}) => {

    return (
        <Link to={`/reservations/reservation-details/${id}`}> 
        {name}
        </Link>
    );
}

ReservationLink.propTypes = {
    id: PropTypes.number.isRequired,
    name: PropTypes.number.isRequired,
};

export default ReservationLink;