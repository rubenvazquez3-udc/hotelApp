import React, { useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { FormattedMessage } from 'react-intl';
import { useHistory } from 'react-router-dom';

import { Errors } from '../../common';
import * as actions from '../actions';


import reservation from '../../reservation';

const AddGuest = () => {

    const dispatch = useDispatch();
    const history = useHistory();
    const [dni, setDni] = useState('');
    const [name, setName] = useState('');
    const [surname, setSurname] = useState('');
    const [address, setAddress] = useState('');
    const [phoneNumber, setPhoneNumber] = useState('');
    const reservation1 = useSelector(reservation.selectors.getReservation);
    

    const [backendErrors, setBackendErrors] = useState(null);

    let form;

    const handleSubmit = event => {
        
        event.preventDefault();

        if (form.checkValidity()) {

            dispatch(actions.addGuest(
                {reservation: reservation1,
                guest:{
                    name: name,
                    surname: surname,
                    address: address,
                    dni: dni,
                    phoneNumber: phoneNumber
                }
                },
                () => history.push('/guests'),
                errors => setBackendErrors(errors)
            ));


        } else {

            setBackendErrors(null);
            form.classList.add('was-validated');

        }

    }


    return (
        <div>
            <Errors errors={backendErrors} onClose={() => setBackendErrors(null)} />
            <div className="card bg-light border-dark">
                <h5 className="card-header">
                    <FormattedMessage id="project.hotels.AddGuest.title" />
                </h5>
                <div className="card-body">
                    <form ref={node => form = node}
                        className="needs-validation" noValidate
                        onSubmit={e => handleSubmit(e)}>
                        <div className="form-group row">
                            <label htmlFor="name" className="col-md-3 col-form-label">
                                <FormattedMessage id="project.global.fields.firstName" />
                            </label>
                            <div className="col-md-4">
                                <input type="text" id="name" className="form-control"
                                    value={name}
                                    onChange={e => setName(e.target.value)}
                                    autoFocus
                                    required />
                                <div className="invalid-feedback">
                                    <FormattedMessage id='project.global.validator.required' />
                                </div>
                            </div>
                        </div>
                        <div className="form-group row">
                            <label htmlFor="surname" className="col-md-3 col-form-label">
                                <FormattedMessage id="project.global.fields.lastName" />
                            </label>
                            <div className="col-md-4">
                                <input type="text" id="surname" className="form-control"
                                    value={surname}
                                    onChange={e => setSurname(e.target.value)}
                                    required />
                                <div className="invalid-feedback">
                                    <FormattedMessage id='project.global.validator.required' />
                                </div>
                            </div>
                        </div>
                        <div className="form-group row">
                            <label htmlFor="dni" className="col-md-3 col-form-label">
                                <FormattedMessage id="project.global.fields.id" />
                            </label>
                            <div className="col-md-4">
                                <input type="text" id="dni" className="form-control"
                                    value={dni}
                                    onChange={e => setDni(e.target.value)}
                                    placeholder='DNI, NIE, Passport'
                                    required />
                                <div className="invalid-feedback">
                                    <FormattedMessage id='project.global.validator.required' />
                                </div>
                            </div>
                        </div>
                        <div className="form-group row">
                            <label htmlFor="address" className="col-md-3 col-form-label">
                                <FormattedMessage id="project.global.fields.address" />
                            </label>
                            <div className="col-md-4">
                                <input type="text" id="adress" className="form-control"
                                    value={address}
                                    onChange={e => setAddress(e.target.value)}
                                    required />
                                <div className="invalid-feedback">
                                    <FormattedMessage id='project.global.validator.required' />
                                </div>
                            </div>
                        </div>
                        <div className="form-group row">
                            <label htmlFor="phoneNumber" className="col-md-3 col-form-label">
                                <FormattedMessage id="project.global.fields.phone" />
                            </label>
                            <div className="col-md-4">
                                <input type="text" id="phoneNumber" className="form-control"
                                    value={phoneNumber}
                                    onChange={e => setPhoneNumber(e.target.value)}
                                    required />
                                <div className="invalid-feedback">
                                    <FormattedMessage id='project.global.validator.required' />
                                </div>
                            </div>
                        </div>
                        <div className="form-group row">
                            <div className="offset-md-3 col-md-2">
                                <button type="submit" className="btn btn-primary">
                                    <FormattedMessage id="project.global.buttons.save" />
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    );

}

export default AddGuest;