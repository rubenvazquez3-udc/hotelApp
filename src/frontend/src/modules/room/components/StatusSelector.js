import React from 'react';
import PropTypes from 'prop-types';


import { FormattedMessage } from "react-intl";

const StatusSelector = (selectProps) => {
    const status = [
        {id: 0, name: "LIBRE", label: "LIBRE"},
        {id: 1, name: "OCUPADA", label: "OCUPADA"},
        {id: 2, name: "SIN_LIMPIAR", label: "SIN LIMPIAR"},
        {id: 3, name: "NO_UTILIZABLE", label: "NO UTILIZABLE"}
    ];

    return (
        <select {...selectProps}>
            <FormattedMessage id='project.global.fields.roomStatus'>
                {message => (<option value="">{message}</option>)}
            </FormattedMessage>

            {status && status.map(st => 
                <option key={st.id} value={st.name}>{st.label}</option>)}
        </select>
    );
}

StatusSelector.propTypes = {
    selectProps: PropTypes.object
};

export default StatusSelector;