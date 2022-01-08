import React, { useState } from 'react'
import axios from 'axios'
import { Form, Button, ListGroup } from 'react-bootstrap';
import * as urls from "./../URL"
import toast, { Toaster } from 'react-hot-toast';

function Comments({ version }) {
  const [comments, setComments] = useState([]);
  const [newComment, setNewComment] = useState("");

  const userId = localStorage.getItem("user")
  const username = localStorage.getItem("username")

  React.useEffect(() => {

    axios.get(urls.baseURL + `comments/version/` + version)
      .then(res => {
        setComments(res.data);
        console.log(version);
      })
      .catch(function (error) {
        console.log(error);
      });
  }, [version]);

  function handleSubmit(event) {
    event.preventDefault();
    toast.loading("Your request is being processed", {
      duration: 5000
    })
    axios.post(urls.baseURL + 'comments/create', {
      "userId": userId,
      "username": username,
      "versionId": version,
      "text": newComment,
      "solution": false
    })
      .then(function (response) {
        axios.get(urls.baseURL + `comments/version/` + version)
          .then(res => {
            setComments(res.data);
            setNewComment("");
          })
      })
      .catch(error => {
        if (error != null) {
          alert("Something went wrong when creating your comment. " + error)
          console.log(error);
          return false
        }
        else {
          alert("Something went wrong when creating your comment!")
          return false;
        }

      });
  }

  function handleTextInputChange(e) {
    setNewComment(e.target.value);
  }

  return (

    <>
      <div><Toaster /></div>
      <ul className="collection w-100 p-3">
        {comments.map(comment => (
          <ListGroup as="ol" numbered>
            <ListGroup.Item
              as="li"
              className="d-flex justify-content-between align-items-start mb-3"
            >
              <div className="ms-2 me-auto">
                <div className="fw-bold">{comment.username}</div>
                <p>{comment.text}</p>
              </div>
              {comment.solution
                ? <Button className="likebtn ms-1" variant="primary" size="sm">
                  <i class="fas fa-check"></i>
                </Button>
                : <div></div>
              }

            </ListGroup.Item>
          </ListGroup>
        ))}
      </ul>


      <div className="p-3 pt-0" style={{ background: "#f2f2f2" }}>
        <Form onSubmit={handleSubmit}>
          <Form.Group className="mb-3" controlId="title">
            <Form.Label className="fw-bold pt-2">Sugest a solution</Form.Label>
            <Form.Control
              as="textarea"
              placeholder="Leave a comment here"
              id="comment"
              value={newComment}
              onChange={handleTextInputChange}
              style={{ height: '100px' }}
              required />
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