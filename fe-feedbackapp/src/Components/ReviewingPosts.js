import axios from 'axios';
import React, {useState, useEffect} from 'react';
import { ListGroup } from 'react-bootstrap';
import { Link } from 'react-router-dom';
import "./css/ViewPosts.scss";

function ReviewingPost({openSelectedPost}){

    const [posts, setPosts] = useState([]);
    const [apiNotLoaded, setApiNotLoaded] = useState(false);

    useEffect(() =>{
        let reviewerId = 6;
        getPosts(reviewerId);
    },[]
    )

    async function getPosts(reviewerId){
        try{
          const response = await axios.get('http://localhost:8080/post/postsToReview', 
          {params:{reviewerId:reviewerId}})
          setPosts(response.data)
        }
        catch(e){
            console.log("no post find for this reviewer")
            setApiNotLoaded(true)
        }
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
                            pathname: "/post", 
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
                    apiNotLoaded ?
                    (
                        <p>Server error. Please reload!</p>
                    )
                    :
                    (
                        <p>You do not have any posts to review</p>
                    )
                )}
            </ListGroup>
        </>
    )
}

export default ReviewingPost;