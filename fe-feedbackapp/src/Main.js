import React from 'react';
import Footer from './Components/Footer';
import NavBar from './Components/NavBar';
import ReplyBox from './Components/ReplyBox';
import  Comments  from './Components/Comments';


function Main(props) {
    return (
        <>
         <NavBar />
         <div className ='comment-section'>
             <div className='comments'>
             <Comments />
             </div>
             <div className='reply-box'>
             <ReplyBox />
             </div>
         </div>
         
         <Footer />   
        </>
    );
}

export default Main;