import axios from 'axios';
import React, {useState, useEffect} from 'react';
import { ListGroup } from 'react-bootstrap';
import { Link } from 'react-router-dom';
import "./css/ViewPosts.scss";
import * as urls from "../URL";

function ReviewingPost({openSelectedPost}){

    const [posts, setPosts] = useState([]);
    
    useEffect(() =>{
        setTimeout(function() {
            const userId = localStorage.getItem("user")
            getPosts(userId);
        }, 50);
        
    },[]
    )

    function getPosts(reviewerId){
        axios.get(urls.baseURL + 'post/postsToReview', {params:{reviewerId:reviewerId}}).
        then((response) => {
            setPosts(response.data);
        })
        .catch((e) => {
            console.log("no posts find for this reviewer!");
        });
    }

    const selectPost = (post) => {
        openSelectedPost(post);
    }

    return(
        <>
            <ListGroup>
                {posts.length > 0 ? (
                    posts.map((post)=>(
                        <Link to={{ 
                            pathname: "/fe/selectedPost",
                            state: post.postId 
                           }}
                           className="text-decoration-none">
                        <ListGroup.Item className = "activePost" key={post} onClick={() => selectPost(post)}>
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
                    <p>You do not have any posts to review</p>
                )}
            </ListGroup>
        </>
    )
}

export default ReviewingPost;