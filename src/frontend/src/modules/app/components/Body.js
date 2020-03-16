import React from 'react';
import {useSelector} from 'react-redux';
import {Route, Switch} from 'react-router-dom';

import AppGlobalComponents from './AppGlobalComponents';
import Home from './Home';
import {Login, SignUp, UpdateProfile, ChangePassword, Logout} from '../../users';
import users from '../../users';
import {FindProductsResult, ProductDetails} from '../../catalog';
import {ShoppingCart, Buy, PurchaseCompleted, FindOrders, FindOrdersResult, OrderDetails} from '../../shopping';

const Body = () => {

    const loggedIn = useSelector(users.selectors.isLoggedIn);
    
   return (

        <div className="container">
            <br/>
            <AppGlobalComponents/>
            <Switch>
                <Route exact path="/"><Home/></Route>
                <Route exact path="/catalog/find-products-result"><FindProductsResult/></Route>
                <Route exact path="/catalog/product-details/:id"><ProductDetails/></Route>
                {loggedIn && <Route exact path="/shopping/shopping-cart"><ShoppingCart/></Route>}
                {loggedIn && <Route exact path="/shopping/buy"><Buy/></Route>}
                {loggedIn && <Route exact path="/shopping/purchase-completed"><PurchaseCompleted/></Route>}
                {loggedIn && <Route exact path="/shopping/find-orders"><FindOrders/></Route>}
                {loggedIn && <Route exact path="/shopping/find-orders-result"><FindOrdersResult/></Route>}
                {loggedIn && <Route exact path="/shopping/order-details/:id"><OrderDetails/></Route>}
                {loggedIn && <Route exact path="/users/update-profile"><UpdateProfile/></Route>}
                {loggedIn && <Route exact path="/users/change-password"><ChangePassword/></Route>}
                {loggedIn && <Route exact path="/users/logout"><Logout/></Route>}
                {!loggedIn && <Route exact path="/users/login"><Login/></Route>}
                {!loggedIn && <Route exact path="/users/signup"><SignUp/></Route>}
                <Route><Home/></Route>
            </Switch>
        </div>

    );

};

export default Body;
