import React, { useState, useEffect } from 'react';
import axios from "axios";
import { Form, Button } from 'react-bootstrap';
import SelectReviewers from './SelectReviewers';
import "./css/CreatePost.scss";
import { useHistory } from "react-router-dom";
import * as urls from "./../URL"
import toast, { Toaster } from 'react-hot-toast';

function CreatePostForm(props) {
  let history = useHistory();

  const [post, setPost] = useState({
    idOP: null,
    title: "",
    category: "",
    description: "",
    filePath: "",
    reviewersIds: [],
  });

  useEffect(() => {
    const userId = localStorage.getItem("user")
    setPost({
      ...post,
      idOP: userId
    })
  }, [])

  const [reviewersIdsList, setReviewersIdsList] = useState([]);


  const isValidHttpUrl = (string) => {
    let url;
    try {

      url = new URL(string);

    } catch (_) {

      return false;

    }
    return url.protocol === "http:" || url.protocol === "https:";
  }

  //method to add post in BE
  async function addPostInBe() {
    toast.loading("Your request is being processed",{
      duration: 5000
    })
    var returnValue = null;
    // check if URL valid
    if (!isValidHttpUrl(post.filePath)) {
      alert('The url is not valid. Please retry.');
      return false;
    }

    const postToSend = {
      idOP: post.idOP,
      title: post.title,
      category: post.category,
      description: post.description,
      filePath: post.filePath,
      reviewersIds: reviewersIdsList,
    }
    await axios.post(urls.baseURL + 'post/create', postToSend
    ).then(response => {
      if (response.data) {
        console.log(response.data)
        returnValue = true;
      }
    }).catch(error => {
      console.log(error);
      if (error != null) {
        alert("Something when wrong when creating your post. " + error)
      }
      else {
        alert("Something went wrong and your post is not created!")
        return false;
      }
    });
    return returnValue;
  }

  //onchange method for textfields of post
  const changeHandler = e => {
    setPost({
      ...post,
      [e.target.name]: e.target.value
    })
  }

  //onSubmit method for the form, calling the addInBe method
  async function createPost(e) {
    e.preventDefault();

    if (await addPostInBe() === true) {
      //clear the state
      setPost({
        idOP: "",
        title: "",
        category: "",
        filePath: "",
        description: "",
        reviewersIds: []
      })
      // redirect to Home page
      history.push({ pathname: "/", state: { createdPost: post } });
    }
  }


  const addReviewerId = (reviewerId) => {
    setReviewersIdsList([...reviewersIdsList, reviewerId]);
    // setPost({...post, reviewersIds: reviewersIdsList});
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
      <div><Toaster /></div>
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
        <Form.Group className="mb-3" controlId="filepath">
          <Form.Label className="create-post-lb">Paste file path here</Form.Label>
          <Form.Control size="lg" type="text" placeholder="Filepath" name="filePath" onChange={changeHandler} required />
          <Form.Text className="text-muted">
            One drive url required
          </Form.Text>
        </Form.Group>
        {/* <Form.Group controlId="formFile" className="mb-3">
          <Form.Label className="create-post-lb">Upload file</Form.Label>
          <Form.Control type="file" name="files" onChange={onFileChange} />
        </Form.Group> */}

        <SelectReviewers addReviewerId={addReviewerId} removeReviewerId={removeReviewerId} />
        <Button variant="primary" type="submit">
          Submit
        </Button>
      </Form>
    </>
  );
}

export default CreatePostForm;