import * as actions from "./actions";
import * as actionTypes from "./actionTypes";
import reducer from "./reducer";
import * as selectors from "./selectors";

export {default as AddHotel} from "./components/addhotel";
export {default as HotelDetails} from "./components/HotelDetails";
export {default as RemoveHotel} from "./components/RemoveHotel";




export default {actions, actionTypes, reducer, selectors};