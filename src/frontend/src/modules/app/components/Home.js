import React from 'react';
import { FormattedMessage } from 'react-intl';
import FindHotels from '../../hotel/components/FindHotels';


/* Contenido de la web inicial*/
const Home = () =>(
    <div className="text-center">

        <FindHotels/>
        <FormattedMessage id="project.app.Home.welcome"/>
    </div>
);

export default Home;
