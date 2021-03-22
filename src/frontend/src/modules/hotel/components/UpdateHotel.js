import React, {useState} from 'react';
import {useDispatch, useSelector} from 'react-redux';
import {FormattedMessage} from 'react-intl';
import {useHistory} from 'react-router-dom';

import {Errors} from '../../common';
import * as actions from '../actions';
import * as selectors from '../selectors';

const UpdateHotel = () => {

    const hotel = useSelector(selectors.getHotel);

    //console.log(hotel);

    const dispatch = useDispatch();
    const history = useHistory();
    const [name, setHotelName] = useState(hotel.name);
    const [manager, setHotelManager] = useState(hotel.manager);
    const [address, setHotelAddress] = useState(hotel.address);
    const [phoneNumber, setPhoneNumber] = useState(hotel.phoneNumber);
    const [description, setDescription] = useState(hotel.description);
    
    const [backendErrors, setBackendErrors] = useState(null);

    let form;

    const handleSubmit = event => {

        event.preventDefault();

        if (form.checkValidity()) {            
            dispatch(actions.updateHotel(
                {id: hotel.id,
                name: name.trim(),
                manager: manager.trim(),
                address: address.trim(),
                phoneNumber: phoneNumber.trim(),
                description: description.trim()},
                () => history.push('/'),
                errors => setBackendErrors(errors)));

        } else {

            setBackendErrors(null);
            form.classList.add('was-validated');

        }

    }


    return (
        <div>
            <Errors errors={backendErrors} onClose={() => setBackendErrors(null)}/>
            <div className="card bg-light border-dark">
                <h5 className="card-header">
                    <FormattedMessage id="project.hotels.UpdateHotel.title"/>
                </h5>
                <div className="card-body">
                    <form ref={node => form = node}
                        className="needs-validation" noValidate onSubmit={e => handleSubmit(e)}>
                        <div className="form-group row">
                            <label htmlFor="name" className="col-md-3 col-form-label">
                                <FormattedMessage id="project.global.fields.hotelName"/>
                            </label>
                            <div className="col-md-4">
                                <input type="text" id="name" className="form-control" disabled
                                    value={name}
                                    onChange={e => setHotelName(e.target.value)}
                                    autoFocus
                                    required/>
                                <div className="invalid-feedback">
                                    <FormattedMessage id='project.global.validator.required'/>
                                </div>
                            </div>
                        </div>
                        <div className="form-group row">
                            <label htmlFor="manager" className="col-md-3 col-form-label">
                                <FormattedMessage id="project.global.fields.hotelManager"/>
                            </label>
                            <div className="col-md-4">
                                <input type="text" id="manager" className="form-control"
                                    value={manager}
                                    onChange={e => setHotelManager(e.target.value)}
                                    required/>
                                <div className="invalid-feedback">
                                    <FormattedMessage id='project.global.validator.required'/>
                                </div>
                            </div>
                        </div>
                        <div className="form-group row">
                            <label htmlFor="address" className="col-md-3 col-form-label">
                                <FormattedMessage id="project.global.fields.address"/>
                            </label>
                            <div className="col-md-4">
                                <input type="text" id="address" className="form-control" disabled
                                    value={address}
                                    onChange={e => setHotelAddress(e.target.value)}
                                    required/>
                                <div className="invalid-feedback">
                                    <FormattedMessage id='project.global.validator.required'/>
                                </div>
                            </div>
                        </div>
                        <div className="form-group row">
                            <label htmlFor="description" className="col-md-3 col-form-label">
                                <FormattedMessage id="project.global.fields.address"/>
                            </label>
                            <div className="col-md-4">
                                <textarea id="description" maxLength="249" placeholder="Descripcion" className="form-control"
                                    value={description}
                                    onChange={e => setDescription(e.target.value)}
                                    required/>
                                <div className="invalid-feedback">
                                    <FormattedMessage id='project.global.validator.required'/>
                                </div>
                            </div>
                        </div>
                        <div className="form-group row">
                            <label htmlFor="phoneNumber" className="col-md-3 col-form-label">
                                <FormattedMessage id="project.global.fields.phone"/>
                            </label>
                            <div className="col-md-4">
                                <input type="tel" id="phoneNumber" className="form-control"
                                    value={phoneNumber}
                                    onChange={e => setPhoneNumber(e.target.value)}
                                    required/>
                                <div className="invalid-feedback">
                                    <FormattedMessage id='project.global.validator.required'/>
                                </div>
                            </div>
                        </div>
                        
                        <div className="form-group row">
                            <div className="offset-md-3 col-md-2">
                                <button type="submit" className="btn btn-primary">
                                    <FormattedMessage id="project.hotels.UpdateHotel.title"/>
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    );
    
}

export default UpdateHotel;