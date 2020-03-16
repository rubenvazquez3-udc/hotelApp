import {config, appFetch} from './appFetch';

export const addToShoppingCart = (shoppingCartId, productId, quantity, onSuccess, 
    onErrors) =>
    appFetch(`/shopping/shoppingcarts/${shoppingCartId}/addToShoppingCart`, 
        config('POST', {productId, quantity}), onSuccess, onErrors);

export const updateShoppingCartItemQuantity = (shoppingCartId, productId,
    quantity, onSuccess, onErrors) =>
    appFetch(`/shopping/shoppingcarts/${shoppingCartId}/updateShoppingCartItemQuantity`, 
        config('POST', {productId, quantity}), onSuccess, onErrors);

export const removeShoppingCartItem = (shoppingCartId, productId, onSuccess,
    onErrors) =>
    appFetch(`/shopping/shoppingcarts/${shoppingCartId}/removeShoppingCartItem`, 
        config('POST', {productId}), onSuccess, onErrors);

export const buy = (shoppingCartId, postalAddress, postalCode, onSuccess, 
    onErrors) =>
    appFetch(`/shopping/shoppingcarts/${shoppingCartId}/buy`, 
        config('POST', {postalAddress, postalCode}), onSuccess, onErrors);

export const findOrders = ({page}, onSuccess) => 
    appFetch(`/shopping/orders?page=${page}`, config('GET'), onSuccess);

export const findOrder = (orderId, onSuccess) =>
    appFetch(`/shopping/orders/${orderId}`, config('GET'), onSuccess);
