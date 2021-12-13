import React, {useState, useEffect} from 'react';
import { ListGroup } from 'react-bootstrap';
import  "./css/ViewPosts.scss";
import axios from "axios";
import { Link } from 'react-router-dom';
import * as urls from "./../URL"


function MyPosts({openSelectedPost}){

    const [myPosts, setMyPosts] = useState([]);

    useEffect(() => {
        setTimeout(function() {
            const userId = localStorage.getItem("user")
            getMyPosts(userId);    
        }, 50);
        
    }, []);

    function getMyPosts(idOP) {
        // get my posts with GET request from the API
        axios.get(urls.baseURL + 'post', { params: { idOP: idOP } }).
        then((response) => {
            setMyPosts(response.data);
        })
        .catch((e) => {
            console.log("no post found for this user!");
        })
    }


    const selectPost = (post) => {
        openSelectedPost(post);
    }

    return(
        <>
            <ListGroup>
                {myPosts.length > 0 ? (
                    myPosts.map((post) => (
                        <Link to={{ 
                            pathname: "/fe/selectedPost",
                            state: post.postId 
                           }}
                           className="text-decoration-none">
                        <ListGroup.Item className="activePost" key={post} onClick={() => selectPost(post)}>
                            <div className="postTitle">{post.title}</div>
                            <div className="postDate post-info">Posted on: {post.postDate}</div>
                            <div className="resolveDate post-info">
                                {post.resolveDate === "" ? 
                                <div>Resolved on: {post.resolveDate}</div>
                                :
                                (
                                <div>Not resolved</div>
                                )}
                            </div>
                            
                        </ListGroup.Item>
                        </Link>
                    ))
                ) : 
                (
                    <p>You do not have any created posts</p>
                )}
                
            </ListGroup>
        </>
    )
}

export default MyPosts