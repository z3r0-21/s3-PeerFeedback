import React, {useState, useEffect} from 'react';
import { ListGroup } from 'react-bootstrap';
import styles from "./css/ViewPosts.scss";

function ReviewingPost(){
    return(
        <>

            <ListGroup>
                <ListGroup.Item className="activePost">other Post1</ListGroup.Item>
                <ListGroup.Item className="activePost">other Post2</ListGroup.Item>
            </ListGroup>
        </>
    )
}

export default ReviewingPost;