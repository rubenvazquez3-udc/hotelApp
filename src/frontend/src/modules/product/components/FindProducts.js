import React, { useState, useEffect  } from 'react';
import { useDispatch, useSelector } from "react-redux"
import FindProductsResult from './FindProductsResult';
import * as actions from '../actions';
import hotel from "../../hotel";

const FindProducts = () => {

    const dispatch = useDispatch();
    const [name, setName] = useState('');
    const hotels = useSelector(hotel.selectors.getHotel);
    
    const hotelid = hotels.id;

    useEffect(() => {
        dispatch(actions.findProducts({hotelid:hotelid, name: name.trim(), page:0}));
    }, [hotelid,name, dispatch]);

    const handleSubmit = event => {
        event.preventDefault();

        dispatch(actions.findProducts({hotelid:hotelid, name:name.trim() ,page:0}));
    }

    /*
     <button type="submit" className="btn btn-primary my-2 my-sm-0">
        <FormattedMessage id='project.global.buttons.search' />
    </button>
    */

    return (
    <div>
        <div className="formulario">
        <form className="form-inline mt-2 mt-md-0" onSubmit={e => handleSubmit(e)}>

            <input id="productName" type="text" className="form-control mr-sm-2" placeholder="Producto"
                value={name} onChange={e => setName(e.target.value)} />
        </form>
        </div>
        <br/>
        <FindProductsResult/>
        </div>
    )

}

export default FindProducts;