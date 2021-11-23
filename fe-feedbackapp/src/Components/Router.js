import React from 'react';
import { Route, Switch } from 'react-router-dom';
import ViewPosts from './ViewPosts';
import Post from './Post';

const Routers = () => {
    return (
        <Switch>
            <Route exact path="/" component={ViewPosts} />
            <Route exact path="/post" component={Post} />
            
        </Switch>
    );
}

export default Routers;