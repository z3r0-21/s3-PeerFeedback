import React, {useState, useEffect} from 'react';
import {Form, Button, InputGroup, FormControl, Tabs, Tab} from "react-bootstrap";
import MyPosts from './MyPosts';
import ReviewingPosts from './ReviewingPosts';
import { useHistory } from "react-router-dom";


function ViewPosts() {
    const [key, setKey] = useState('myPosts');
    const [selectedPost, setSelectedPost] = useState();
    let history = useHistory();


    const openSelectedPost = (post) => {
      setSelectedPost(post);
      console.log(post.postId);
    }

    return (
      <div className="posts-tabs">
      <Tabs
        id="ViewPosts"
        activeKey={key}
        onSelect={(k) => setKey(k)}
        className="viewPosts"
      >
        <Tab eventKey="myPosts" title="My Posts">
          <MyPosts openSelectedPost={openSelectedPost}/>
        </Tab>

        <Tab eventKey="postsToReview" title="Posts to Review">
          <ReviewingPosts openSelectedPost={openSelectedPost}/>
        </Tab>
      </Tabs>
      </div>
    );
  }
  
export default ViewPosts;