import React from "react";
import PropTypes from "prop-types";

import {Link} from 'react-router-dom';


const HotelLink = ({id, name}) => {

    return (
        <Link to={`/hotels/hotel-details/${id}`}> 
        {name}
        </Link>
    );
}

HotelLink.propTypes = {
    id: PropTypes.number.isRequired,
    name: PropTypes.string.isRequired,
};

export default HotelLink;