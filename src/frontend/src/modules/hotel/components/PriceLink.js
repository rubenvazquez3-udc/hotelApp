import React from "react";
import PropTypes from "prop-types";

import {Link} from 'react-router-dom';


const PriceLink = ({id, name}) => {

    return (
        <Link to={`/hotel/prices/${id}`}> 
        {name}
        </Link>
    );
}

PriceLink.propTypes = {
    id: PropTypes.number.isRequired,
    name: PropTypes.number.isRequired,
};

export default PriceLink;