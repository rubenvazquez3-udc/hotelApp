import React from 'react';
import {FormattedMessage} from 'react-intl';

const Footer = () => (

    <div>
        <br/>
        <hr/>
        <footer>
            <p className="text-center">
                &copy; <FormattedMessage id="project.app.Footer.text"/>
            </p>
        </footer>
    </div>

);

export default Footer;
