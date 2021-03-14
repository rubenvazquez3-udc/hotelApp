import React from 'react';
import {useSelector} from 'react-redux';
import {Route, Switch} from 'react-router-dom';

import AppGlobalComponents from './AppGlobalComponents';
import Home from './Home';
import {Login, SignUp, UpdateProfile, ChangePassword, Logout,CreateManagerAcccount, CreateHotelPersonalAccount} from '../../users';
import users from '../../users';
import {HotelDetails, AddHotel} from '../../hotel';





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
