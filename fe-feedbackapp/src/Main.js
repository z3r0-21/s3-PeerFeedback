import React from 'react';
import Footer from './Components/Footer';
import NavBar from './Components/NavBar';
import SelectReviewers from './Components/SelectReviewers';

function Main(props) {
    return (
        <>
         <NavBar />
         <SelectReviewers/>
         <Footer />   
        </>
    );
}

export default Main;