import React, { useState, useEffect  } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { FormattedMessage } from 'react-intl';
import { useHistory } from 'react-router-dom';

import { Errors } from '../../common';
import * as actions from '../actions';
import * as selectors from "../selectors";

import users from "../../users";
import hotel from "../../hotel";
import RoomSelector from '../components/RoomSelector';



const AssignRoom = () => {

    const dispatch = useDispatch();
    const history = useHistory();

    const user = useSelector(users.selectors.getUser);
    const hotels = useSelector(hotel.selectors.getHotels);
    const rooms = useSelector(selectors.getAvailableRooms);
    const reservation = useSelector(selectors.getReservation);

    const [roomid, setRoomId] = useState('');

    const [backendErrors, setBackendErrors] = useState(null);

    let form;

    const hotel1 = hotels.filter(hotel => hotel.address === user.address);
    const hotelid = hotel1[0].id;
    const typename = reservation.roomtype.name;

    useEffect(() => {
        dispatch(actions.findAvailableRooms(hotelid, typename));
        
    }, [typename,hotelid, dispatch]);
    

    const handleSubmit = event => {
        
        event.preventDefault();
        
        const room1 = {...rooms.filter(room => room.id === parseInt(roomid,10))};

        if (form.checkValidity()) {

            dispatch(actions.assignRoom(
                {
                reservation: reservation,
                room: room1[0],
                begin: reservation.inbound,
                end: reservation.outbound
            }),
            alert('Room Assigned successfully'),
            
            errors => setBackendErrors(errors)
            );

            history.push('/reservations');
            

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
                            <label htmlFor="room" className="col-md-3 col-form-label">
                                <FormattedMessage id="project.global.fields.roomNumber" />
                            </label>
                            <div className="col-md-4">
                                <RoomSelector id="roomid" className='custom-select my-1 mr-sm-2' value={roomid} 
                                    onChange = {e => setRoomId(e.target.value)}/>
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


export default AssignRoom;