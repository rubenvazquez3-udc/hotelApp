import React, {useState} from 'react';
import {useSelector, useDispatch} from 'react-redux';
import {FormattedMessage} from 'react-intl';
import {useHistory} from 'react-router-dom';

import {Errors} from '../../common';
import * as actions from '../actions';
import * as selectors from '../selectors';

const BuyForm = () => {

    const shoppingCart = useSelector(selectors.getShoppingCart);
    const dispatch = useDispatch();
    const history = useHistory();
    const [postalAddress, setPostalAddress] = useState('');
    const [postalCode, setPostalCode] = useState('');
    const [backendErrors, setBackendErrors] = useState(null);
    let form;

    const handleSubmit = event => {

        event.preventDefault();

        if (form.checkValidity()) {

            dispatch(actions.buy(shoppingCart.id, 
                postalAddress.trim(), postalCode.trim(), 
                () => history.push('/shopping/purchase-completed'),
                errors => setBackendErrors(errors)));

        } else {
            setBackendErrors(null);
            form.classList.add('was-validated');
        }

    }

    return (

        <div>
            <Errors errors={backendErrors}
                onClose={() => setBackendErrors(null)}/>
            <div className="card bg-light border-dark">
                <h5 className="card-header">
                    <FormattedMessage id="project.shopping.BuyForm.title"/>
                </h5>
                <div className="card-body">
                    <form ref={node => form = node}
                        className="needs-validation" noValidate 
                        onSubmit={(e) => handleSubmit(e)}>
                        <div className="form-group row">
                            <label htmlFor="postalAddress" className="col-md-3 col-form-label">
                                <FormattedMessage id="project.global.fields.postalAddress"/>
                            </label>
                            <div className="col-md-4">
                                <input type="text" id="postalAddress" className="form-control"
                                    value={postalAddress}
                                    onChange={e => setPostalAddress(e.target.value)}
                                    autoFocus
                                    required/>
                                <div className="invalid-feedback">
                                    <FormattedMessage id='project.global.validator.required'/>
                                </div>
                            </div>
                        </div>
                        <div className="form-group row">
                            <label htmlFor="postalCode" className="col-md-3 col-form-label">
                                <FormattedMessage id="project.global.fields.postalCode"/>
                            </label>
                            <div className="col-md-4">
                                <input type="text" id="postalCode" className="form-control"
                                    value={postalCode}
                                    onChange={e => setPostalCode(e.target.value)}
                                    required/>
                                <div className="invalid-feedback">
                                    <FormattedMessage id='project.global.validator.required'/>
                                </div>
                            </div>
                        </div>
                        <div className="form-group row">
                            <div className="offset-md-3 col-md-1">
                                <button type="submit" className="btn btn-primary">
                                    <FormattedMessage id="project.global.buttons.buy"/>
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>

    );

}

export default BuyForm;
