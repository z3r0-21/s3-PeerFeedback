import React, { useState, useEffect } from 'react';
import { Form, Button, InputGroup, FormControl, Tabs, Tab } from "react-bootstrap";
import MyPosts from './MyPosts';
import axios from 'axios';
import * as urls from "./../URL"
import ReviewingPosts from './ReviewingPosts';
import { useHistory } from "react-router-dom";


function ViewPosts() {
  const [key, setKey] = useState('myPosts');
  const [selectedPost, setSelectedPost] = useState();

  let history = useHistory();

  useEffect(() => {
    getMyId();
  }, []);

  async function getMyId() {
    // get my id, which is a hash code of my email
    try {
      const response = await axios.get(urls.baseURL + 'users/identity');
      setUserId(response.data);
    }
    catch (e) {
      console.log(e);
    }
  }

  const setUserId = (userId) => {
    localStorage.setItem("user", userId)
  }

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
          <MyPosts
            openSelectedPost={openSelectedPost} />
        </Tab>

        <Tab eventKey="postsToReview" title="Posts to Review">
          <ReviewingPosts
            openSelectedPost={openSelectedPost} />
        </Tab>
      </Tabs>
    </div>
  );
}

export default ViewPosts;


