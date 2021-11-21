import React, {useState, useEffect} from 'react';
import { ListGroup } from 'react-bootstrap';
import styles from "./css/ViewPosts.scss";
import axios from "axios";


function MyPosts(){

    const [myPosts, setMyPosts] = useState([]);
    const [apiNotLoaded, setApiNotLoaded] = useState(false);    

    useEffect(() => {
        // todo: get the logged user
        let idOP = 1;
        getMyPosts(idOP);
    }, []);

    async function getMyPosts(idOP) {
        // get my posts with GET request from the API
        try{
            const response = await axios.get('http://localhost:8080/post', { params: { idOP: idOP } });
            setMyPosts(response.data);
        }
        catch(e){
            console.log(e);
            setApiNotLoaded(true);
        }
        
    }

    const [selectedPost, setSelectedPost] = useState();

    const openSelectedPost =(post) => {
        setSelectedPost(post);
        console.log(post.postId);
    }

    return(
        <>
            <ListGroup>
                {myPosts.length > 0 ? (
                    myPosts.map((post) => (
                        <ListGroup.Item className="activePost" onClick={() => openSelectedPost(post)}>{post.title} [{post.postDate}]</ListGroup.Item>
                    ))
                ) : 
                (
                    apiNotLoaded ?
                    (
                        <p>Server error. Please reload!</p>
                    )
                    :
                    (
                        <p>You do not have any created posts</p>
                    )
                )}
                
            </ListGroup>
        </>
    )
}

export default MyPosts