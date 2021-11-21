import axios from 'axios';
import React, {useState, useEffect} from 'react';
import { ListGroup } from 'react-bootstrap';
import { useParams } from 'react-router';
import styles from "./css/ViewPosts.scss";

function ReviewingPost(){

    const [posts, setPosts] = useState([]);

    useEffect(() =>{
        let reviewerId = 5;
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
        }
    }

    return(
        <>

            <ListGroup>
                
                {posts.map((post)=>(
                    <ListGroup.Item className = "activePost" >
                        {post.title}
                    </ListGroup.Item>
                ))}
            </ListGroup>
        </>
    )
}

export default ReviewingPost;