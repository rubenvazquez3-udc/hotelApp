import React, {useEffect, useState} from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { FormattedMessage } from 'react-intl';
import { useHistory } from 'react-router-dom';

import { Errors } from '../../common';
import * as actions from '../actions';

import hotel from "../../hotel";
import users from "../../users";
import RoomTypeSelector from '../../room/components/RoomTypeSelector';
import room from '../../room';

const AddReservation = () => {

    const dispatch = useDispatch();
    const history = useHistory();
    const user = useSelector(users.selectors.getUser);
    const [typ, setRoomType] = useState('');
    const [date_in, setDateIn] = useState('');
    const [date_out, setDateOut] = useState('');
    const hotel1 = useSelector(hotel.selectors.getHotel);
    const [qantity, setQuantity] = useState('');
    const types = useSelector(room.selectors.getRoomTypes);

    const [backendErrors, setBackendErrors] = useState(null);

    let form;
    const username = user.firstName;

    useEffect(() => {
        dispatch(actions.findReservations('',username,''));

    }, [username, dispatch]);

    const handleSubmit = event => {
        
        event.preventDefault();
        
        const type1 = {...types.filter(t => t.id === parseInt(typ,10))};

        if (form.checkValidity()) {

            dispatch(actions.addReservation(
                {user: user,
                inbound : date_in,
                outbound: date_out,
                roomtype: type1[0],
                hotel: hotel1,
                rooms: qantity
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
                    <FormattedMessage id="project.hotels.AddReservation.title" />
                </h5>
                <div className="card-body">
                    <form ref={node => form = node}
                        className="needs-validation" noValidate
                        onSubmit={e => handleSubmit(e)}>
                        <div className="form-group row">
                            <label htmlFor="date_in" className="col-md-3 col-form-label">
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
                            <label htmlFor="date_out" className="col-md-3 col-form-label">
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
                            <label htmlFor="quantity" className="col-md-3 col-form-label">
                                <FormattedMessage id="project.global.fields.quantity" />
                            </label>
                            <div className="col-md-4">
                                <input type="number" id="address" className="form-control"
                                    value={qantity}
                                    onChange={e => setQuantity(e.target.value)}
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
                                <RoomTypeSelector id="roomtypeid" className='custom-select my-1 mr-sm-2' value={typ} 
                                    onChange = {e => setRoomType(e.target.value)}/>
                                <div className="invalid-feedback">
                                    <FormattedMessage id='project.global.validator.required' />
                                </div>
                            </div>
                        </div>
                        <div className="form-group row">
                            <div className="offset-md-3 col-md-2">
                                <button type="submit" className="btn btn-primary">
                                    <FormattedMessage id="project.hotels.AddReservation.title" />
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    );

}

export default AddReservation;