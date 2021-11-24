import React from 'react';

function SelectedReviewer({reviewer, handleRemoveReviewer}) {

    const handleClick = () => {
        handleRemoveReviewer(reviewer);
    }


    return (
        <div className="reviewer">
            <span className="reviewer-email">{reviewer.id} {reviewer.email} </span>
            <span onClick={handleClick} className="remove-reviewer"><i class="fas fa-window-close"></i></span>
        </div>
    );
}

export default SelectedReviewer;