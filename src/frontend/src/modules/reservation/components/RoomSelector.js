import { useSelector } from "react-redux";
import React from 'react';
import PropTypes from 'prop-types';

import * as selectors from '../selectors';
import { FormattedMessage } from "react-intl";

const RoomSelector = (selectProps) => {
    const rooms = useSelector(selectors.getAvailableRooms);

    return (
        <select {...selectProps}>
            <FormattedMessage id='project.global.fields.roomNumber'>
                {message => (<option value="">{message}</option>)}
            </FormattedMessage>

            {rooms && rooms.map(room => 
                <option key={room.id} value={room.id}>{room.number}</option>)}
        </select>
    );
}

RoomSelector.propTypes = {
    selectProps: PropTypes.object
};

export default RoomSelector;