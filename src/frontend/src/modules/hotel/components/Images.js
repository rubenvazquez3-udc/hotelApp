import React from 'react';
import PropTypes from "prop-types";


const Images = ({images}) => (

    <div id="carouselExampleControls" class="carousel slide" data-bs-ride="carousel">
        <div class="carousel-inner">

            {images.map(i => 
                <div class="carousel-item">
                    <img src={process.env.PUBLIC_URL + `/images/${i.name}`} class="d-block w-25"/>
            </div>
            )}
        </div>
        <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Previous</span>
        </button>
        <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Next</span>
        </button>
    </div>
   
);

Images.propTypes = {
    images: PropTypes.array.isRequired
};

export default Images;