import React, {useState, useEffect} from 'react';
import { ListGroup } from 'react-bootstrap';
import styles from "./css/ViewPosts.scss";

function MyPosts(){

    const [selectPost, setSelectPost] = useState("1");

    const Select=(a)=> {
        setSelectPost(a)
    }

    
    
    return(
        <>
            <ListGroup>
                <ListGroup.Item className="activePost" >My Post1</ListGroup.Item>
                <ListGroup.Item className="activePost" > My Post2</ListGroup.Item>
                <ListGroup.Item className="activePost" > My Post2</ListGroup.Item>
                <ListGroup.Item className="activePost" > My Post2</ListGroup.Item>
                <ListGroup.Item className="activePost" >My Post1</ListGroup.Item>
                <ListGroup.Item className="activePost" > My Post2</ListGroup.Item>
                <ListGroup.Item className="activePost" > My Post2</ListGroup.Item>
                <ListGroup.Item className="activePost" > My Post2</ListGroup.Item>
                
            </ListGroup>
        </>
    )
}

export default MyPosts