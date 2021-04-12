import React from "react";
import PropTypes from "prop-types";

import {Link} from 'react-router-dom';


const RoomLink = ({id, name}) => {

    return (
        <Link to={`/rooms/room-details/${id}`}> 
        {name}
        </Link>
    );
}

RoomLink.propTypes = {
    id: PropTypes.number.isRequired,
    name: PropTypes.number.isRequired,
};

export default RoomLink;