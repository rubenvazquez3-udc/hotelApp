import React, { useState, useEffect } from 'react';
import { useDispatch} from "react-redux"

import * as actions from '../actions';
import FindHotelsResult from "./FindHotelsResult";

const FindHotels = () => {

    const dispatch = useDispatch();
    const [name, setName] = useState('');
    const [address, setAddress] = useState('');


    useEffect(() => {
        dispatch(actions.getHotels({name: name.trim(), address: address.trim(), page:0}));
    }, [ name,address, dispatch]);

    return (
        <div>
            <div className="formulario">
                <form className="form-inline mt-2 mt-md-0">

                    <input id="username" type="text" className="form-control mr-sm-2" placeholder='Name'
                           value={name} onChange={e => setName(e.target.value)} />
                    <input id="address" type="text" className="form-control mr-sm-2" placeholder='Address'
                           value={address} onChange={e => setAddress(e.target.value)} />

                </form>
            </div>
            <br />
            <FindHotelsResult />
        </div>
    )

}

export default FindHotels;