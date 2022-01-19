import React, { useState, useEffect } from 'react';
import { Form, Button, InputGroup, FormControl, Tabs, Tab } from "react-bootstrap";
import { hashString } from "react-hash-string"
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

      // localStorage.setItem("user", -1236026766);
      // localStorage.setItem("username", "a.takev");
      getMyId();

  }, []);

  function getMyId() {
    // get my email
    axios.get(urls.baseURL + 'users/identity'
    ).then(res => {
      //set the hashcode
      console.log("here");
      var userId = hashString(String(res.data))
      setUserId(userId);
      
      //set usernasme
      var parts = String(res.data).split("@")
      var username = parts[0];
      setUsername(username);
    }).catch(e => {
      console.log(e);
    })

    axios.post(
      urls.baseURL + 'users',
      {
        studentNr: localStorage.getItem('user'),
        username: localStorage.getItem('username')
      })
  }

  const setUserId = (userId) => {
    localStorage.setItem("user", userId)
  }

  const setUsername = (userName) => {
    localStorage.setItem("username", userName)
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
            openSelectedPost={openSelectedPost}/>
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


