import React from 'react';
import CreatePostForm from './Components/CreatePostForm';
import Footer from './Components/Footer';
import NavBar from './Components/NavBar';
import ViewPosts from './Components/ViewPosts';

function Main(props) {
    return (
        // <>
        //     <NavBar />
        //     <CreatePostForm />
        //     <Footer />
        // </>
        <>
        <ViewPosts />
        </>
    );
}

export default Main;