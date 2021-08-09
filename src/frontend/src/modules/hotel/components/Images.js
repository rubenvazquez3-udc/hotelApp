import React from 'react';
import PropTypes from "prop-types";


const Images = ({images}) => (

    <div id="carouselExampleControls" className="carousel slide" data-bs-ride="carousel">
        <div className="carousel-inner">

            {images && images.map(i =>
                <div className="carousel-item">
                    <img src={process.env.PUBLIC_URL + `/images/${i.name}`} alt="" className="d-block w-25"/>
            </div>
            )}
        </div>
        <button className="carousel-control-prev" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="prev">
            <span className="carousel-control-prev-icon" aria-hidden="true"/>
            <span className="visually-hidden">Previous</span>
        </button>
        <button className="carousel-control-next" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="next">
            <span className="carousel-control-next-icon" aria-hidden="true"/>
            <span className="visually-hidden">Next</span>
        </button>
    </div>
   
);

Images.propTypes = {
    images: PropTypes.array.isRequired
};

export default Images;