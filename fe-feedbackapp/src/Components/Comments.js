import React, { Component } from 'react'
import axios from 'axios'
import { FloatingLabel, Form, Button, ListGroup, Badge, Image, FormText } from 'react-bootstrap';
import Thumb from '../Components/Thumb.png'

class Comments extends Component{

  state = {
    comments: []
}


handleSubmit(){
  axios.post('http://localhost:8080/comments/create', {
    "versionId": 7,
    "userId": 7,
    "text": document.getElementById("comment").value,
    "solution": false
})
  .then(function (response) {
    console.log(response);
  })
  .catch(function (error) {
    console.log(error);
  });
  window.location.reload(false);
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
   <ul className="collection w-100 p-3">
            {this.state.comments.map(comment => (
              <ListGroup as="ol" numbered>
              <ListGroup.Item
                as="li"
                className="d-flex justify-content-between align-items-start mb-3"
              >
                <div className="ms-2 me-auto">
                  <div className="fw-bold">User</div>
                  <p>{comment.text}</p>
                </div>
                <Badge variant="primary" pill>
                  14
                </Badge>
                <Button className="likebtn ms-1" variant="primary" size="sm">
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

  
          <div  className="p-3 pt-0" style={{background: "#f2f2f2"  }}>
          <Form onSubmit={this.handleSubmit}>
              <Form.Group className="mb-3" controlId="title">
                <Form.Label className="fw-bold pt-2">Sugest a solution</Form.Label>
                <Form.Control 
                as="textarea"
                placeholder="Leave a comment here"
                id="comment"
                style={{ height: '100px' }} />
              </Form.Group>
              <Button variant="outline-primary" type="submit" className="mt-0">
                Submit
              </Button>
            </Form>
            </div>
    </>
    
);
}
};


export default Comments