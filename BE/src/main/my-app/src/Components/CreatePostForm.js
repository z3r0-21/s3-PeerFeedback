import React, { useState } from 'react';
import axios from "axios";
import { Form, Button } from 'react-bootstrap';
import SelectReviewers from './SelectReviewers';
import "./css/CreatePost.scss";
import { useHistory } from "react-router-dom";
import * as urls from "./../URL"

function CreatePostForm(props) {
  let history = useHistory();
  const userId = localStorage.getItem("user")


  const [post, setPost] = useState({
    idOP: userId,
    title: "",
    category: "",
    description: "",
    reviewersIds: [],
  });

  const [reviewersIdsList, setReviewersIdsList] = useState([]);

  //State for the selected file
  const [selectedFile, setSelectedFile] = useState(null);

  //method to add post in BE
  const addPostInBe = (post, file) => {
    console.log(reviewersIdsList);
    file.append("idOP", post.idOP)
    file.append("title", post.title)
    file.append("category", post.category)
    file.append("description", post.description)
    file.append("reviewersIds", reviewersIdsList)

    console.log(file.get("idOP"));
    
    axios.post(urls.baseURL + 'post/create', file)
      .then((response) => {
        console.log(response);
      }, (error) => {
        console.log(error);
      });
  }


  //onchange method for the file, saving it to the state
  const onFileChange = e => {
    setSelectedFile(e.target.files[0])
  }

  //onchange method for textfields of post
  const changeHandler = e => {
    setPost({
       ...post,
        [e.target.name]: e.target.value })
  }

  const saveReviewers = (reviewersList) => {
    console.log("save reviewers");
    // setPost({ ...post, reviewersIds: reviewersList });
  }

  //onSubmit method for the form, calling the addInBe method
  const createPost = e => {
    e.preventDefault();
    //Create formdata for file. Check if not null
    let formData = new FormData()
    if (selectedFile != null) {
      formData.append(
        'uploadedFile',
        selectedFile
      )
    }
    else {
      alert("Please choose a file to upload")
      return;
    }

    addPostInBe(post, formData)

    //clear the state
    setPost({
      idOP: "",
      title: "",
      category: "",
      description: "",
      reviewersIds: []
    })

    //clear the state for the file
    setSelectedFile(null)

    // redirect to Home page
    history.push("/");
  }


  const addReviewerId = (reviewerId) => {
    setReviewersIdsList([...reviewersIdsList, reviewerId]);
  }

  const removeReviewerId = (reviewerId) => {
    setReviewersIdsList([
            ...reviewersIdsList.filter(r => {
                return r !== reviewerId
            })
    ]);
  }

  return (
    <>
      <Form onSubmit={createPost} className="create-post-form">
        <Form.Group className="mb-3" controlId="title">
          <Form.Label className="create-post-lb">Title</Form.Label>
          <Form.Control size="lg" type="text" placeholder="Title" name="title" onChange={changeHandler} required />
          <Form.Text className="text-muted">
            Make sure to choose a descriptive title.
          </Form.Text>
        </Form.Group>
        <Form.Group className="mb-3" controlId="category">
          <Form.Label className="create-post-lb">Category</Form.Label>
          <Form.Select name="category" onChange={changeHandler} required>
            <option value="">Select category</option>
            <option value="Software">Software</option>
            <option value="Media">Media</option>
            <option value="Infrastructure">Infrastructure</option>
          </Form.Select>
        </Form.Group>
        <Form.Group className="mb-3" controlId="description">
          <Form.Label className="create-post-lb">Description</Form.Label>
          <Form.Control as="textarea" rows={5} name="description" onChange={changeHandler} required />
        </Form.Group>
        <Form.Group controlId="formFile" className="mb-3">
          <Form.Label className="create-post-lb">Upload file</Form.Label>
          <Form.Control type="file" name="files" onChange={onFileChange} />
        </Form.Group>

        <SelectReviewers addReviewerId={addReviewerId} removeReviewerId={removeReviewerId} />
        <Button variant="primary" type="submit">
          Submit
        </Button>
      </Form>
    </>
  );
}

export default CreatePostForm;