import React, { useState } from 'react';
import axios from "axios";
import { Form, Button } from 'react-bootstrap';
import SelectReviewers from './SelectReviewers';
import "./css/CreatePost.scss";
import { useHistory } from "react-router-dom";
import * as urls from "./../URL"

function EditPostForm(props) {
  let history = useHistory();
  const userId = localStorage.getItem("user")
    const [version, setVersion] = React.useState();
    const [selectedPost, setSelectedPost] = useState();
    const [post1, setPost1] = React.useState([]);
    const [versionId, setVersionId] = React.useState();

  React.useEffect(() => {
    axios.get(urls.baseURL + "post/" + props.location.state)
    .then((response) => {
        setVersion(1);
        let size = response.data.versions.length;
        console.log("Version: ", response.data.versions[size - 1].versionId);
        setVersionId(response.data.versions[size - 1].versionId);
        setPost1(response.data);

        if(response.data.versions.length){
            setVersion(response.data.versions[0].versionCounter);
        }
    });
}, []);

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
    file.append("idOP", post1.idOP)
    file.append("title", post1.title)
    file.append("category", post1.category)
    file.append("description", post1.description)
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
    setPost1({
       ...post1,
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
    if (selectedFile == null) {
      formData.append(
        'uploadedFile',
        selectedFile
      )
    }
    else {
      alert("Please choose a file to upload")
      return;
    }

    addPostInBe(post1, formData)

    //clear the state
    setPost1({
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
          <Form.Control size="lg" type="text" placeholder="Title" value={post1.title} name="title" onChange={changeHandler} required />
          <Form.Text className="text-muted">
            Make sure to choose a descriptive title.
          </Form.Text>
        </Form.Group>
        <Form.Group className="mb-3" controlId="category">
          <Form.Label className="create-post-lb">Category</Form.Label>
          <Form.Select name="category" onChange={changeHandler} required>
            <option value={post1.category}>{post1.category}</option>
            <option value="Software">Software</option>
            <option value="Media">Media</option>
            <option value="Infrastructure">Infrastructure</option>
          </Form.Select>
        </Form.Group>
        <Form.Group className="mb-3" controlId="description">
          <Form.Label className="create-post-lb">Description</Form.Label>
          <Form.Control as="textarea" rows={5} value={post1.description} name="description" onChange={changeHandler} required />
        </Form.Group>
        <Form.Group controlId="formFile" className="mb-3">
          <Form.Label className="create-post-lb">File URL</Form.Label>
          <Form.Control size="lg" type="text" placeholder="Filepath" value={post1.versions[version - 1].versionId} name="filePath" onChange={changeHandler} required />
          <Form.Text className="text-muted">
            One drive url required
            </Form.Text>
        </Form.Group>

        <SelectReviewers addReviewerId={addReviewerId} removeReviewerId={removeReviewerId} />
        <Button variant="primary" type="submit">
          Submit
        </Button>
      </Form>
    </>
  );
}

export default EditPostForm;