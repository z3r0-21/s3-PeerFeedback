import React, { useState } from 'react';
import axios from "axios";
import { Form, Button } from 'react-bootstrap';
import SelectReviewers from './SelectReviewers';
import CommentSection from './CommentSection';

function CreatePostForm(props) {

  const [post, setPost] = useState([{
    idOP: "",
    title: "",
    category: "",
    description: "",
    reviewersIds: []
  }]);


  const changeHandler = e => {
    setPost({ ...post, [e.target.name]: e.target.value })
  }

  function createPost() {
    axios.post('http://localhost:8080/post/create', {
      idOP: post.idOP,
      title: post.title,
      category: post.category,
      description: post.description,
      reviewersIds: post.reviewersIds
    })
      .then((response) => {
        console.log(response);
      }, (error) => {
        console.log(error);
      });
  }


  const saveReviewers = (reviewersList) => {
    console.log("save reviewers");
    setPost({ ...post, reviewersIds: reviewersList });
  }

  return (
    <>
      <Form>
        <Form.Group className="mb-3" controlId="title">
          <Form.Label>Title</Form.Label>
          <Form.Control size="lg" type="text" placeholder="Title" name="title" onChange={changeHandler} required />
          <Form.Text className="text-muted">
            Make sure to choose a descriptive title.
          </Form.Text>
        </Form.Group>
        <Form.Group className="mb-3" controlId="category">
          <Form.Label>Category</Form.Label>
          <Form.Select name="category" onChange={changeHandler} required>
            <option value="">Select category</option>
            <option value="Software">Software</option>
            <option value="Media">Media</option>
            <option value="Infrastructure">Infrastructure</option>
          </Form.Select>
        </Form.Group>
        <Form.Group className="mb-3" controlId="description">
          <Form.Label>Description</Form.Label>
          <Form.Control as="textarea" rows={5} name="description" onChange={changeHandler} required />
        </Form.Group>
        <Form.Group controlId="formFile" className="mb-3">
          <Form.Label>Upload file</Form.Label>
          <Form.Control type="file" name="files" />
        </Form.Group>

        <SelectReviewers saveReviewers={saveReviewers} />
        <Button variant="primary" type="submit" onClick={createPost}>
          Submit
        </Button>
      </Form>
      <CommentSection />
    </>
  );
}

export default CreatePostForm;