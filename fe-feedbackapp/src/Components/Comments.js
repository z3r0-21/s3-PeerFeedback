import React, { useState } from 'react'
import axios from 'axios'
import { FloatingLabel, Form, Button, ListGroup, Badge, Image, FormText } from 'react-bootstrap';
import Thumb from '../Components/Thumb.png'

function Comments(props) { 
  const [comments, setComments] = useState([]);
  const [newComment, setNewComment] = useState("");

React.useEffect(() => {
  axios.get(`http://localhost:8080/comments`)
  .then(res => {
    setComments(res.data);
  })
  .catch(function (error) {
    console.log(error);
  });
  }, []);

  function handleSubmit(event){
    event.preventDefault();
    axios.post('http://localhost:8080/comments/create', {
      "userId": 2,
      "versionId": 1,
      "text": newComment,
      "solution": false
  })
    .then(function (response) {
      axios.get(`http://localhost:8080/comments`)
      .then(res => {
        setComments(res.data);
        setNewComment("");
      })
    })
    .catch(function (error) {
      console.log(error);
    });
  }

  function handleTextInputChange(e){
    setNewComment(e.target.value);
  }
  
return(

  <>
   <ul className="collection w-100 p-3">
            {comments.map(comment => (
              <ListGroup as="ol" numbered>
              <ListGroup.Item
                as="li"
                className="d-flex justify-content-between align-items-start mb-3"
              >
                <div className="ms-2 me-auto">
                  <div className="fw-bold">User</div>
                  <p>{comment.text}</p>
                </div>
                <Button className="likebtn ms-1" variant="primary" size="sm">
                <i class="fas fa-check"></i>
                </Button>    
              </ListGroup.Item>
            </ListGroup>
            ))}
          </ul>

  
          <div  className="p-3 pt-0" style={{background: "#f2f2f2"  }}>
          <Form onSubmit={handleSubmit}>
              <Form.Group className="mb-3" controlId="title">
                <Form.Label className="fw-bold pt-2">Sugest a solution</Form.Label>
                <Form.Control 
                as="textarea"
                placeholder="Leave a comment here"
                id="comment"
                value={newComment}
                onChange={handleTextInputChange}
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

export default Comments;