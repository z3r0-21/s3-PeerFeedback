import React from 'react';

function SelectedReviewer({reviewer, handleRemoveReviewer}) {

    const handleClick = () => {
        handleRemoveReviewer(reviewer);
    }


    return (
        <div className="reviewer">
            <span>{reviewer.id} {reviewer.email} </span>
            <button onClick={handleClick}><i className="fas fa-times"></i></button>
        </div>
    );
}

export default SelectedReviewer;