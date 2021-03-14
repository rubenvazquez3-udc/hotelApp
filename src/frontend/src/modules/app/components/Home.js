import React from 'react';
import { FormattedMessage } from 'react-intl';
import FindHotelsResult from '../../hotel/components/FindHotelsResult';


/* Contenido de la web inicial*/
const Home = () =>(
    <div className="text-center">

        <FindHotelsResult/>
        <FormattedMessage id="project.app.Home.welcome"/>
    </div>
);

export default Home;
