import * as actions from "./actions";
import * as actionTypes from "./actionTypes";
import reducer from "./reducer";
import * as selectors from "./selectors";

export {default as AddHotel} from "./components/addhotel";
export {default as HotelDetails} from "./components/HotelDetails";
export {default as UpdateHotel} from "./components/UpdateHotel";
export {default as FindHotels} from './components/FindHotels';
export {default as UploadPhoto} from './components/UploadPhoto';
export {default as AddPrice} from './components/AddPrice';
export {default as UpdatePrice} from './components/UpdatePrice';
export {default as PriceDetails} from './components/PriceDetails';




export default {actions, actionTypes, reducer, selectors};