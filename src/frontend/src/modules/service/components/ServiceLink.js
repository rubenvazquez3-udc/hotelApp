import React from "react";
import PropTypes from "prop-types";

import {Link} from 'react-router-dom';


const ServiceLink = ({id, name}) => {

    return (
        <Link to={`/services/details/${id}`}> 
        {name}
        </Link>
    );
}

ServiceLink.propTypes = {
    id: PropTypes.number.isRequired,
    name: PropTypes.string.isRequired,
};

export default ServiceLink;
