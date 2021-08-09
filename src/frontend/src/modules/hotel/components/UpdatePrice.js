import React, { useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { FormattedMessage } from 'react-intl';
import { useHistory } from 'react-router-dom';

import { Errors } from '../../common';
import * as actions from '../actions';
import * as selectors from '../selectors';

import RoomTypeSelector from '../../room/components/RoomTypeSelector';

const UpdatePrice = () => {

    const dispatch = useDispatch();
    const history = useHistory();
    const price = useSelector(selectors.getPrice);
    const [priceunit, setPrice] = useState(price.price);
    const [type] = useState(price.type.id);
    const hotel = useSelector(selectors.getHotel);

    const [backendErrors, setBackendErrors] = useState(null);

    let form;

    const handleSubmit = event => {

        event.preventDefault();

        if (form.checkValidity()) {

            dispatch(actions.updatePrice(
                {
                    id: price.id,
                    price: priceunit,
                    hotel: hotel,
                    type: price.type
                },
                () => history.push(`/hotels/hotel-details/${hotel.id}`),
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
                    <FormattedMessage id="project.hotels.UpdatePrice.title" />
                </h5>
                <div className="card-body">
                    <form ref={node => form = node}
                        className="needs-validation" noValidate
                        onSubmit={e => handleSubmit(e)}>
                        <div className="form-group row">
                            <label htmlFor="price" className="col-md-3 col-form-label">
                                <FormattedMessage id="project.global.fields.price" />
                            </label>
                            <div className="col-md-4">
                                <input type="number" id="price" className="form-control"
                                    value={priceunit}
                                    onChange={e => setPrice(e.target.value)}
                                    autoFocus
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
                                    value={type} readOnly/>
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

export default UpdatePrice;