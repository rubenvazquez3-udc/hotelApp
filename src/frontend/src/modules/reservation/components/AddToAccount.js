import React, { useState } from 'react';
import { useDispatch } from 'react-redux';
import { FormattedMessage } from 'react-intl';
import PropTypes from 'prop-types';
//import { useHistory } from 'react-router-dom';

import { Errors, Success } from '../../common';
import * as actions from '../actions';


const AddToAccount = ({productId, reservationId, serviceId}) => {

    const dispatch = useDispatch();
    //const history = useHistory();
    const [quantity, setQuantity] = useState(1);
    const [backendErrors, setBackendErrors] = useState(null);

    let form;

    const handleSubmit = event => {

        event.preventDefault();

        if (form.checkValidity()) {

            dispatch(actions.AddToAccount(reservationId, 
                {
                productId: productId,
                serviceId: serviceId,
                quantity: quantity
            },
            () => console.log('Success'),//<Success message ={"Added Succesfully"} onClose={}/>,
            errors => setBackendErrors(errors)
            ));


        } else {

            setBackendErrors(null);
            form.classList.add('was-validated');

        }

    }
    return (
        <div>
            <Errors errors={backendErrors} onClose={() => setBackendErrors(null)}/>
            <form ref={node => form = node}
                className="needs-validation" noValidate
                onSubmit= {e => handleSubmit(e)}>
                <div className="form-group row">
                    <label htmlFor="price" className="offset-md-5 col-md-1 col-form-label">
                        <FormattedMessage id="project.global.fields.shopping.quantity" />
                    </label>
                    <div className="col-md-2">
                        <input type="number" id="price" className="form-control" 
                            value={quantity} 
                            onChange={e => setQuantity(Number(e.target.value))} 
                            autoFocus
                            min="1"/>
                        <div className="invalid-feedback">
                            <FormattedMessage id='project.global.validator.incorrectQuantity' />
                        </div>
                    </div>
                </div>
                <div className="form-group row">
                    <div className="offset-md-6 col-md-2">
                        <button type="submit" className="btn btn-primary">
                            <FormattedMessage id="project.services.AddToAccount.title" />
                        </button>
                    </div>
                </div>
                </form>
        </div>
    );

}

AddToAccount.propTypes = {
    productId: PropTypes.number.isRequired,
    reservationId: PropTypes.number.isRequired,
    serviceId: PropTypes.number.isRequired
};

export default AddToAccount;