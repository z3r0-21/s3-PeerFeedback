import React from 'react';
import { Route, Switch } from 'react-router-dom';
import ViewPosts from './ViewPosts';
import Post from './Post';
import CreatePostForm from './CreatePostForm';


const Routers = () => {
    return (
        <Switch>
            <Route exact path="/" component={ViewPosts} />
            <Route path="/fe/selectedPost" component={Post} />
            <Route path="/fe/createPost" component={CreatePostForm} />
        </Switch>
    );
}

export default Routers;