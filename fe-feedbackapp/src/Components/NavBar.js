import { logDOM } from '@testing-library/dom';
import React from 'react';
import { Navbar, Nav, Container } from 'react-bootstrap';
import {LinkContainer} from "react-router-bootstrap";
import { BrowserRouter} from 'react-router-dom';
import styles from "./css/Navbar.scss";

function NavBar() {
    return (
        <>
        <BrowserRouter>
            <Navbar collapseOnSelect expand="md" bg="primary" variant="dark">
            <Container fluid>
            <Navbar.Brand>
                
                <span>Fontys Peer Review</span>
            </Navbar.Brand>
            <Navbar.Toggle aria-controls="responsive-navbar-nav" />
            <Navbar.Collapse id="responsive-navbar-nav">
                <Nav>
                    <LinkContainer to="#" exact>
                        <Nav.Link >Home</Nav.Link>
                    </LinkContainer>
                    <LinkContainer to="#" exact>
                        <Nav.Link >About</Nav.Link>
                    </LinkContainer>
                    <LinkContainer to="#" exact>
                        <Nav.Link >Contacts</Nav.Link>
                    </LinkContainer>
                    <LinkContainer to="#" exact className="profile-avatar">
                        <i className="fas fa-user-circle"/>
                    </LinkContainer>
                </Nav>
            </Navbar.Collapse>
            </Container>
            </Navbar>
        </BrowserRouter>
        </>
    );
}

export default NavBar;