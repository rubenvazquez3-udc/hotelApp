import React, { useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { FormattedMessage } from 'react-intl';
import { useHistory } from 'react-router-dom';

import { Errors } from '../../common';
import * as actions from '../actions';
import * as selectors from "../selectors";

import users from "../../users";


const UploadPhoto = () => {

    const dispatch = useDispatch();
    const history = useHistory();

    const user = useSelector(users.selectors.getUser);
    const hotels = useSelector(selectors.getHotels);

    const [file, setFile] = useState('');
    const [backendErrors, setBackendErrors] = useState(null);

    let form;

    const hotel1 = hotels.filter(hotel => hotel.address === user.address);


    const handleSubmit = event => {

        event.preventDefault();

        if (form.checkValidity()) {
            console.log(file);

            dispatch(actions.uploadPhoto(hotel1[0].id, file),
                () => history.push('/'),
                errors => setBackendErrors(errors)
            );

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
                    <FormattedMessage id="project.hotels.UploadPhoto.title" />
                </h5>
                <div className="card-body">
                    <form ref={node => form = node} encType="multipart/form-data"
                          className="needs-validation" noValidate
                          onSubmit={e => handleSubmit(e)}>

                        <div className="form-group row">
                            <label htmlFor="room" className="col-md-3 col-form-label">
                                <FormattedMessage id="project.global.fields.roomNumber" />
                            </label>
                            <div className="col-md-4">
                                <input type="file" id="file" className="form-control"
                                       value={file}
                                       onChange={e => setFile(e.target.value)}
                                       autoFocus
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

export default UploadPhoto;