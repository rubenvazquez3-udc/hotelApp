import React from 'react';
import {useSelector} from 'react-redux';
import {Route, Switch} from 'react-router-dom';

import AppGlobalComponents from './AppGlobalComponents';
import Home from './Home';
import {Login, SignUp, UpdateProfile, ChangePassword, Logout,CreateManagerAcccount, CreateHotelPersonalAccount} from '../../users';
import users from '../../users';
import {HotelDetails, AddHotel, UpdateHotel} from '../../hotel';
import {AddRoom, FindRooms, FindRoomsResult, RoomDetails, UpdateRoom} from '../../room';
import {AddReservation, FindReservationsHotel, FindReservationsUser, ReservationDetails, UpdateReservation,AssignRoom} from '../../reservation';






const Body = () => {

    const loggedIn = useSelector(users.selectors.isLoggedIn);
    const userRole = useSelector(users.selectors.getUserRole);
    
   return (

        <div className="container">
            <br/>
            <AppGlobalComponents/>
            <Switch>
                <Route exact path="/"><Home/></Route>
                <Route exact path="/hotels/hotel-details/:id"><HotelDetails/></Route>
                <Route exact path='/hotels/hotel-details/:id/update'><UpdateHotel/></Route>
                <Route exact path='/rooms'><FindRooms/></Route>
                <Route exact path='/rooms/find-rooms-result'><FindRoomsResult/></Route>
                <Route exact path='/rooms/room-details/:id'><RoomDetails/></Route>
                <Route exact path='/hotels/hotel-details/:id/add-rooms'><AddRoom/></Route>
                <Route exact path='/hotels/room-details/:id/update'><UpdateRoom/></Route>
                <Route exact path='/hotels/hotel-details/:id/reservations'><AddReservation/></Route>
                {userRole === 'USER' && <Route exact path='/reservations'><FindReservationsUser/></Route>}
                {userRole !== 'USER' && <Route exact path='/reservations'><FindReservationsHotel/></Route>}
                <Route exact path='/reservations/reservation-details/:id'><ReservationDetails/></Route>
                <Route exact path='/reservations/reservation-details/:id/update'><UpdateReservation/></Route>
                <Route exact path='/reservations/reservation-details/:id/assignRoom'><AssignRoom/></Route>
                {loggedIn && <Route exact path="/users/update-profile"><UpdateProfile/></Route>}
                {loggedIn && <Route exact path="/users/change-password"><ChangePassword/></Route>}
                {loggedIn && <Route exact path="/users/logout"><Logout/></Route>}
                {!loggedIn && <Route exact path="/users/login"><Login/></Route>}
                {!loggedIn && <Route exact path="/users/signup"><SignUp/></Route>}
                {userRole === "ADMIN" && <Route exact path="/users/admin"><CreateManagerAcccount/></Route>}
                {userRole === "MANAGER" && <Route exact path="/users/manager"><CreateHotelPersonalAccount/></Route>}
                {loggedIn && <Route exact path="/hotels"><AddHotel/></Route>}
                <Route><Home/></Route>
            </Switch>
        </div>

    );

};

export default Body;
