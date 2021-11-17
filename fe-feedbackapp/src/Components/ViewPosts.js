import React, {useState, useEffect} from 'react';
import {Form, Button, InputGroup, FormControl, Tabs, Tab} from "react-bootstrap";
import MyPosts from './MyPosts';
import ReviewingPosts from './ReviewingPosts';

function ViewPosts() {
    const [key, setKey] = useState('myPosts');
  
    function handleSelectedPost(currPost) {
      
    }

    return (
      <div className="posts-tabs">
      <Tabs
        id="ViewPosts"
        activeKey={key}
        onSelect={(k) => setKey(k)}
        className="viewPosts"
      >
        <Tab eventKey="myPosts" title="MyPosts">
          <MyPosts/>
        </Tab>

        <Tab eventKey="othersPosts" title="OthersPosts">
          <ReviewingPosts/>
        </Tab>
      </Tabs>
      </div>
    );
  }
  
export default ViewPosts;