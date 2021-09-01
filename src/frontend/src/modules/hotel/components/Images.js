import React from 'react';
import PropTypes from "prop-types";


/*
*  <button className="carousel-control-prev" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="prev">
            <span className="carousel-control-prev-icon" aria-hidden="true"/>
            <span className="visually-hidden"/>
        </button>
        <button className="carousel-control-next" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="next">
            <span className="carousel-control-next-icon" aria-hidden="true"/>
            <span className="visually-hidden"/>
        </button>
        */

const Images = ({images}) => (

    <div id="carouselExampleControls" className="carousel slide" data-bs-ride="carousel">
        <div className="carousel-inner">
            {images && images.map(i =>
                <div className="carousel-item active" key={i.id}>
                    <img src={`/images/${i.name}`} alt={i.name}  className="d-block w-100"/>
                </div>
            )}
        </div>

    </div>
   
);

Images.propTypes = {
    images: PropTypes.array.isRequired
};

export default Images;