import React from 'react';
import CreatePostForm from './Components/CreatePostForm';
import Footer from './Components/Footer';
import NavBar from './Components/NavBar';


function Main(props) {
    return (
        <>
            <NavBar />
            <CreatePostForm />
            <Footer />
        </>
    );
}

export default Main;