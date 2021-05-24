import React, { useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { FormattedMessage } from 'react-intl';
import { useHistory } from 'react-router-dom';

import { Errors } from '../../common';
import * as actions from '../actions';
import * as selectors from '../selectors';
import RoomTypeSelector from '../../room/components/RoomTypeSelector';
import room from '../../room';

const UpdateReservation = () => {

    const reservation = useSelector(selectors.getReservation);
    const types = useSelector(room.selectors.getRoomTypes);

    const dispatch = useDispatch();
    const history = useHistory();

    const [date_in, setDateIn] = useState(reservation.inbound);
    const [date_out, setDateOut]= useState(reservation.outbound);
    const [quantity, setQuantity] = useState(reservation.rooms);
    const [type, setRoomType] = useState(reservation.roomtype.id);

    const [backendErrors, setBackendErrors] = useState(null);

    let form;

    const handleSubmit = event => {

        event.preventDefault();

        if (form.checkValidity()) {

            const typeName = {...types.filter(rt => rt.id === parseInt(type,10))};

            dispatch(actions.updateReservation(
                {
                    id: reservation.id,
                    user: reservation.user,
                    roomtype: typeName[0],
                    hotel: reservation.hotel,
                    inbound: date_in,
                    outbound: date_out,
                    rooms: quantity
                },
                () => history.push('/reservations'),
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
                    <FormattedMessage id="project.reservations.UpdateReservation.title" />
                </h5>
                <div className="card-body">
                    <form ref={node => form = node}
                        className="needs-validation" noValidate
                        onSubmit={e => handleSubmit(e)}>
                        <div className="form-group row">
                            <label htmlFor="number" className="col-md-3 col-form-label">
                                <FormattedMessage id="project.global.fields.date.in" />
                            </label>
                            <div className="col-md-4">
                                <input type="date" id="date_in" className="form-control"
                                    value={date_in}
                                    onChange={e => setDateIn(e.target.value)}
                                    autoFocus
                                    required />
                                <div className="invalid-feedback">
                                    <FormattedMessage id='project.global.validator.required' />
                                </div>
                            </div>
                        </div>
                        <div className="form-group row">
                            <label htmlFor="status" className="col-md-3 col-form-label">
                                <FormattedMessage id="project.global.fields.date.out" />
                            </label>
                            <div className="col-md-4">
                                <input type="date" id="date_out" className="form-control"
                                    value={date_out}
                                    onChange={e => setDateOut(e.target.value)}
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
                            <label htmlFor="number" className="col-md-3 col-form-label">
                                <FormattedMessage id="project.global.fields.quantity" />
                            </label>
                            <div className="col-md-4">
                                <input type="number" id="quantity" className="form-control"
                                    value={quantity}
                                    onChange={e => setQuantity(e.target.value)}
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
                                    <FormattedMessage id="project.reservations.UpdateReservation.title" />
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    );

}

export default UpdateReservation;