import React from 'react';
import { FormattedMessage } from "react-intl";
import { useDispatch, useSelector } from "react-redux"
import { Pager } from '../../common';

import * as selectors from '../selectors';
import * as actions from '../actions';
import Rooms from './Rooms';


const FindRoomsResult = () => {

    const roomSearch = useSelector(selectors.getRooms);
    const dispatch = useDispatch();

    if (!roomSearch) {
        return null;
    }

    if (roomSearch.rooms.items.length === 0) {
        return (
            <div className="alert alert-danger" role="alert" >
                <FormattedMessage id='project.room.FindRoomsResult.noRoomsFound' />
            </div>
        );
    }


    return (
        <div>
            <Rooms rooms={roomSearch.rooms.items} />
            <Pager
                back={{
                    enabled: roomSearch.criteria.page >=1,
                    onClick: () => dispatch(actions.previousFindRoomsResultPage(roomSearch.criteria))
                }}

                next= {{
                    enabled: roomSearch.rooms.existMoreItems,
                    onClick: () => dispatch(actions.nextFindRoomsResultPage(roomSearch.criteria))
                }} />
        </div>
    );

}

export default FindRoomsResult;