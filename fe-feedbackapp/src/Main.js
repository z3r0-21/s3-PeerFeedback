import React from 'react';
import CreatePostForm from './Components/CreatePostForm';
import Footer from './Components/Footer';
import NavBar from './Components/NavBar';
import Comments from './Components/Comments';
import VersionSelection from './Components/VersionSelection';
import Post from './Components/Post';

function Main(props) {
    return (
        <>
            {/* <NavBar />
            <CreatePostForm /> */}
            <Post postId={4}/>
            <Footer />
        </>
    );
}

export default Main;