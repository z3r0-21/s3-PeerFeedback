import React from 'react';
import CreatePostForm from './Components/CreatePostForm';
import Footer from './Components/Footer';
import NavBar from './Components/NavBar';
<<<<<<< HEAD
import ReplyBox from './Components/ReplyBox';
import  Comments  from './Components/Comments';

=======
import SelectReviewers from './Components/SelectReviewers';
>>>>>>> a3e4b19675cc178c84e16f62eec7c9ea0c2b2df0

function Main(props) {
    return (
        <>
         <NavBar />
<<<<<<< HEAD
         <div className ='comment-section'>
             <div className='comments'>
             <Comments />
             </div>
             <div className='reply-box'>
             <ReplyBox />
             </div>
         </div>
         
=======
         <CreatePostForm/>
>>>>>>> a3e4b19675cc178c84e16f62eec7c9ea0c2b2df0
         <Footer />   
        </>
    );
}

export default Main;