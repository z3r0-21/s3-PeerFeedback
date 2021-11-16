import React, { Component } from 'react'
import axios from 'axios'
import { FloatingLabel, Form, Button, ListGroup, Badge, Image } from 'react-bootstrap';
import Thumb from '../Components/Thumb.png'

class Comments extends Component{

  state = {
    comments: []
}


 componentDidMount(){
  axios.get(`http://localhost:8080/comments`).then(res => {
  const comments = res.data;
  this.setState({ comments });
});
  }

render(){
return(

  <>
   <ul className="collection">
            {this.state.comments.map(comment => (
              <ListGroup as="ol" numbered>
              <ListGroup.Item
                as="li"
                className="d-flex justify-content-between align-items-start"
              >
                <div className="ms-2 me-auto">
                  <div><p>User</p></div>
                  <p>{comment.text}</p>
                </div>
                <Badge variant="primary" pill>
                  14
                </Badge>
                <Button className="likebtn" variant="primary" size="sm">
               Like|
               <Image className="thumb"
          roundedCircle
          src={Thumb}
        />
               <span className="visually-hidden">unread messages</span>
                </Button>    
              </ListGroup.Item>
            </ListGroup>
            ))}
          </ul>

  
  <FloatingLabel controlId="floatingTextarea" label="Comments" className="mb-3">
  </FloatingLabel>Sugest a solution<FloatingLabel controlId="floatingTextarea2" label="Add comment here">
      <Form.Control
        as="textarea"
        placeholder="Leave a comment here"
        style={{ height: '100px' }} />
    </FloatingLabel>
    <Button variant="outline-primary">Submit</Button>{' '}
    </>
);
}
};


export default Comments