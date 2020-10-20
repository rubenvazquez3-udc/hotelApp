import {init} from './appFetch';
import * as userService from './userService';
import * as hotelService from './hotelService';
import * as roomService from './roomService';
import * as reservationService from './reservationService';

export {default as NetworkError} from "./NetworkError";

export default {init, userService, hotelService, roomService, reservationService};
