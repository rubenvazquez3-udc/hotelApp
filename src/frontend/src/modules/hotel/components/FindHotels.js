import {useDispatch} from 'react-redux';
import { useHistory } from "react-router-dom";
import * as actions from "../actions";

const FindHotels = () => {
    const history = useHistory();
    const dispatch = useDispatch();

    dispatch(actions.getHotels());
    history.push('/hotels/find-hotels-result');

    return null;

}

export default FindHotels;