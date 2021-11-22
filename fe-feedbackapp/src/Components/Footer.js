import React from 'react';
import logo from './images/fontys-logo.png';
import "./css/Footer.scss";

function Footer(props) {
    return (
        <div className="footer">
            <div className="footer-logo">
                <img
                    src={logo}
                    width="120"
                    height="80"
                />
            </div>
            <div>&copy; {new Date().getFullYear()} Copyright: Fontys Peer Review</div> 
        </div>
    );
}

export default Footer;