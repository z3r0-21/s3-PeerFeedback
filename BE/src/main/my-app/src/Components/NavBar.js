import { logDOM } from '@testing-library/dom';
import React from 'react';
import { Navbar, Nav, Container, NavDropdown } from 'react-bootstrap';
import {LinkContainer} from "react-router-bootstrap";
import { BrowserRouter} from 'react-router-dom';
import styles from "./css/Navbar.scss";
import Router from "./Router";


function NavBar() {
    return (
        <>
        <BrowserRouter>
            <Navbar collapseOnSelect expand="lg" bg="primary" variant="dark" className="main-navbar">
            <Container fluid>
            <Navbar.Brand>
                <span>Fontys Peer Review</span>
            </Navbar.Brand>
            <Navbar.Toggle aria-controls="responsive-navbar-nav" />
            <Navbar.Collapse id="responsive-navbar-nav">
                <Nav>
                    <LinkContainer to="/" exact>
                        <Nav.Link >Home</Nav.Link>
                    </LinkContainer>
                    <LinkContainer to="/createPost" exact>
                        <Nav.Link >Create post</Nav.Link>
                    </LinkContainer>
                    <LinkContainer to="/about" exact>
                        <Nav.Link >About</Nav.Link>
                    </LinkContainer>
                    <LinkContainer to="contacts" exact>
                        <Nav.Link >Contacts</Nav.Link>
                    </LinkContainer>
                    <NavDropdown title={<i className="fas fa-user-circle"/>} className="profile-avatar" id="navbarScrollingDropdown">
                    <NavDropdown.Item >Peter Vladimirov</NavDropdown.Item>
                    <NavDropdown.Item href="#">
                    <div className="view-account-link">View account</div>
                        {/* <LinkContainer to="#" exact>
                            View account
                        </LinkContainer> */}
                    </NavDropdown.Item>
                    <NavDropdown.Divider />
                    <NavDropdown.Item href="#">
                        <div className="sign-out-link">Sign out</div>
                        {/* <LinkContainer to="#" exact>
                            Sign out
                        </LinkContainer> */}
                    </NavDropdown.Item>
                    </NavDropdown>
                    {/* <LinkContainer to="#" exact className="profile-avatar" id="navbarScrollingDropdown">
                        <i className="fas fa-user-circle"/>
                    </LinkContainer> */}
                </Nav>
            </Navbar.Collapse>
            </Container>
            </Navbar>
            <Router/>
        </BrowserRouter>
        </>
    );
}

export default NavBar;