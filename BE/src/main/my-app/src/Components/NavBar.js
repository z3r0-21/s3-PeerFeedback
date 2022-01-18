import { logDOM } from '@testing-library/dom';
import React, { useState, useEffect } from 'react';
import { Navbar, Nav, Container, NavDropdown } from 'react-bootstrap';
import { LinkContainer } from "react-router-bootstrap";
import { BrowserRouter } from 'react-router-dom';
import styles from "./css/Navbar.scss";
import Router from "./Router";
import axios from 'axios';
import toast from 'react-hot-toast';
import Cookies from 'universal-cookie';
import * as urls from "./../URL"

function NavBar() {
    const [username, setUsername] = useState();
    const [userId, setUserId] = useState();

    useEffect(() => {
        setTimeout(function () {
            const localStorageUsername = localStorage.getItem("username")
            setUsername(localStorageUsername);

            const localStorageUserId = localStorage.getItem("user")
            setUserId(localStorageUserId);
        }, 500);
    }, [])

    const handleDelete = (userId) => {

        console.log(userId)
        if (window.confirm('Are you sure to delete this profile?')) {
            console.log("User id: ", userId);
            axios.delete(`${urls.baseURL}deleteData/user/${userId}`)

            toast("The profile has been deleted")
            window.location.href="https://login.windows.net/common/oauth2/logout";
            
        }
    }

    const handleSignOut = () => {
        window.location.href="https://login.windows.net/common/oauth2/logout";
    }

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
                                <LinkContainer to="/fe/createPost" exact>
                                    <Nav.Link >Create post</Nav.Link>
                                </LinkContainer>
                                <LinkContainer to="/about" exact>
                                    <Nav.Link >About</Nav.Link>
                                </LinkContainer>
                                <LinkContainer to="contacts" exact>
                                    <Nav.Link >Contacts</Nav.Link>
                                </LinkContainer>
                                <NavDropdown title={<i className="fas fa-user-circle" />} className="profile-avatar" id="navbarScrollingDropdown">
                                    <NavDropdown.Item >{username}</NavDropdown.Item>
                                    <NavDropdown.Divider />
                                    <NavDropdown.Item href="#">
                                        <div className="sign-out-link" onClick={handleSignOut}>Sign out</div>
                                        {/* <LinkContainer to="#" exact>
                            Sign out
                        </LinkContainer> */}
                                    </NavDropdown.Item>
                                    <NavDropdown.Item href="#">
                                        <div className="sign-out-link" onClick={() => handleDelete(userId)}>Delete Account</div>
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
                <Router />
            </BrowserRouter>
        </>
    );
}

export default NavBar;