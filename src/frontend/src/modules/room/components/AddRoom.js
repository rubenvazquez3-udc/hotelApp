import React, { useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { FormattedMessage } from 'react-intl';
import { useHistory } from 'react-router-dom';

import { Errors } from '../../common';
import * as actions from '../actions';
import hotels from '../../hotel';

const AddRoom = () => {

    const dispatch = useDispatch();
    const history = useHistory();
    const [number, setRoomNumber] = useState('');
    const [status, setRoomStatus] = useState('');
    const [type, setRoomType] = useState('');
    const hotel = useSelector(hotels.selectors.getHotel);

    const [backendErrors, setBackendErrors] = useState(null);

    let form;

    const handleSubmit = event => {

        event.preventDefault();

        if (form.checkValidity()) {

            dispatch(actions.addRoom(
                {
                    number: number,
                    status: status.trim(),
                    type: {
                        name: type.trim()
                    },
                    hotel: hotel
                },
                () => history.push('/rooms/find-rooms-result'),
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
                    <FormattedMessage id="project.hotels.AddRoom.title" />
                </h5>
                <div className="card-body">
                    <form ref={node => form = node}
                        className="needs-validation" noValidate
                        onSubmit={e => handleSubmit(e)}>
                        <div className="form-group row">
                            <label htmlFor="number" className="col-md-3 col-form-label">
                                <FormattedMessage id="project.global.fields.roomNumber" />
                            </label>
                            <div className="col-md-4">
                                <input type="number" id="number" className="form-control"
                                    value={number}
                                    onChange={e => setRoomNumber(e.target.value)}
                                    autoFocus
                                    required />
                                <div className="invalid-feedback">
                                    <FormattedMessage id='project.global.validator.required' />
                                </div>
                            </div>
                        </div>
                        <div className="form-group row">
                            <label htmlFor="status" className="col-md-3 col-form-label">
                                <FormattedMessage id="project.global.fields.roomStatus" />
                            </label>
                            <div className="col-md-4">
                                <input type="text" id="status" className="form-control"
                                    value={status}
                                    onChange={e => setRoomStatus(e.target.value)}
                                    required />
                                <div className="invalid-feedback">
                                    <FormattedMessage id='project.global.validator.required' />
                                </div>
                            </div>
                        </div>
                        <div className="form-group row">
                            <label htmlFor="type" className="col-md-3 col-form-label">
                                <FormattedMessage id="project.global.fields.type" />
                            </label>
                            <div className="col-md-4">
                                <input type="text" id="type" className="form-control"
                                    value={type}
                                    onChange={e => setRoomType(e.target.value)}
                                    required />
                                <div className="invalid-feedback">
                                    <FormattedMessage id='project.global.validator.required' />
                                </div>
                            </div>
                        </div>
                        <div className="form-group row">
                            <div className="offset-md-3 col-md-2">
                                <button type="submit" className="btn btn-primary">
                                    <FormattedMessage id="project.hotels.AddRoom.title" />
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    );

}

export default AddRoom;