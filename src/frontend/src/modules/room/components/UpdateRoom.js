import React, { useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { FormattedMessage } from 'react-intl';
import { useHistory } from 'react-router-dom';

import { Errors } from '../../common';
import * as actions from '../actions';
import * as selectors from '../selectors';
import RoomTypeSelector from './RoomTypeSelector';

const UpdateRoom = () => {

    const room = useSelector(selectors.getRoom);
    const types = useSelector(selectors.getRoomTypes);

    const dispatch = useDispatch();
    const history = useHistory();
    const [number, setNumber] = useState(room.number);
    const [status, setRoomStatus] = useState(room.status);
    const [type, setRoomType] = useState(room.type.id);

    const [backendErrors, setBackendErrors] = useState(null);

    let form;

    const handleSubmit = event => {

        event.preventDefault();

        if (form.checkValidity()) {

            const typeName = {...types.filter(rt => rt.id === parseInt(type,10))};
            
            dispatch(actions.updateRoom(
                {
                    id: room.id,
                    number: number,
                    status: status.trim(),
                    type: typeName[0],
                    hotel: room.hotel
                },
                () => history.push('/rooms'),
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
                    <FormattedMessage id="project.room.UpdateRoom.title" />
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
                                <input type="number" id="number" className="form-control" disabled
                                    value={number}
                                    onChange={e => setNumber(e.target.value)}
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
                                <RoomTypeSelector id='type' className='custom-select my-1 mr-sm-2' 
                                    value={type} onChange={e => setRoomType(e.target.value)} />
                                <div className="invalid-feedback">
                                    <FormattedMessage id='project.global.validator.required' />
                                </div>
                            </div>
                        </div>
                        <div className="form-group row">
                            <div className="offset-md-3 col-md-2">
                                <button type="submit" className="btn btn-primary">
                                    <FormattedMessage id="project.room.UpdateRoom.title" />
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    );

}

export default UpdateRoom;