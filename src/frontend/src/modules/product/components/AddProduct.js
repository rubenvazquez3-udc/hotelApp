import React, { useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { FormattedMessage } from 'react-intl';
import { useHistory } from 'react-router-dom';

import { Errors } from '../../common';
import * as actions from '../actions';

import hotels from '../../hotel';
import users from '../../users';

const AddProduct = () => {

    const dispatch = useDispatch();
    const history = useHistory();
    const [name, setName] = useState('');
    const [description, setDescription] = useState('');
    const [price, setPrice] = useState('');
    const hotelList = useSelector(hotels.selectors.getHotels);
    const user = useSelector(users.selectors.getUser);
    

    const [backendErrors, setBackendErrors] = useState(null);

    let form;

    const handleSubmit = event => {

        event.preventDefault();

        const hotel = hotelList.filter(h => h.address === user.address);

        if (form.checkValidity()) {

            dispatch(actions.addProduct(
                {
                    name: name.trim(),
                    description: description.trim(),
                    price: price,
                    hotel: hotel[0]
                },
                 () => history.push(`/hotels/hotel-details/${hotel[0].id}`),
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
                    <FormattedMessage id="project.product.AddProduct.title" />
                </h5>
                <div className="card-body">
                    <form ref={node => form = node}
                        className="needs-validation" noValidate
                        onSubmit={e => handleSubmit(e)}>
                        <div className="form-group row">
                            <label htmlFor="name" className="col-md-3 col-form-label">
                                <FormattedMessage id="project.global.fields.product" />
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
                            <label htmlFor="description" className="col-md-3 col-form-label">
                                <FormattedMessage id="project.global.fields.description" />
                            </label>
                            <div className="col-md-4">
                            <textarea id="description" maxLength="249" placeholder="Descripcion" className="form-control"
                                    value={description}
                                    onChange={e => setDescription(e.target.value)}
                                    required />
                                <div className="invalid-feedback">
                                    <FormattedMessage id='project.global.validator.required' />
                                </div>
                            </div>
                        </div>
                        <div className="form-group row">
                            <label htmlFor="price" className="col-md-3 col-form-label">
                                <FormattedMessage id="project.global.fields.price" />
                            </label>
                            <div className="col-md-4">
                                <input type="number" id="price" className="form-control"
                                    value={price}
                                    onChange={e => setPrice(e.target.value)}
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

export default AddProduct;