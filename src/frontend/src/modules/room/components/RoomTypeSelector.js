import { useSelector } from "react-redux";
import React from 'react';
import PropTypes from 'prop-types';

import * as selectors from '../selectors';
import { FormattedMessage } from "react-intl";

const RoomTypeSelector = (selectProps) => {
    const roomtypes = useSelector(selectors.getRoomTypes);

    return (
        <select {...selectProps}>
            <FormattedMessage id='project.room.RoomTypeSelector'>
                {message => (<option value="">{message}</option>)}
            </FormattedMessage>

            {roomtypes && roomtypes.map(type => 
                <option key={type.id} value={type.id}>{type.name}</option>)}
        </select>
    );
}

RoomTypeSelector.propTypes = {
    selectProps: PropTypes.object
};

export default RoomTypeSelector;