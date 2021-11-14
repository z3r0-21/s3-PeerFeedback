import React from 'react';
import CreatePostForm from './Components/CreatePostForm';
import Footer from './Components/Footer';
import NavBar from './Components/NavBar';
import styles from "./Components/css/Main.scss";


function Main(props) {
    return (
        <>
            <NavBar />
            {/* <CreatePostForm /> */}
            <Footer />
        </>
    );
}

export default Main;