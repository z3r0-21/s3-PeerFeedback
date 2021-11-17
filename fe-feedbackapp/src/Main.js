import React from 'react';
import CreatePostForm from './Components/CreatePostForm';
import Footer from './Components/Footer';
import NavBar from './Components/NavBar';
import Comments from './Components/Comments';

function Main(props) {
    return (
        <>
            <NavBar />
            <CreatePostForm />
            <Comments />
            <Footer />
        </>
    );
}

export default Main;