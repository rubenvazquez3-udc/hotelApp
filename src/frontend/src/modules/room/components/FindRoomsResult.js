import React from 'react';
import { FormattedMessage } from "react-intl";
import { useSelector } from "react-redux"

import * as selectors from '../selectors';
import Rooms from './Rooms';


const FindRoomsResult = () => {

    const roomSearch = useSelector(selectors.getRooms);

    if (!roomSearch) {
        return null;
    }

    if (roomSearch.length === 0) {
        return (
            <div className="alert alert-danger" role="alert" >
                <FormattedMessage id='project.room.FindRoomsResult.noRoomsFound' />
            </div>
        );
    }

    return (
        <div>
            <Rooms rooms={roomSearch} />
        </div>

    );

}

export default FindRoomsResult;