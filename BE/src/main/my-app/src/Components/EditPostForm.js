import React, { useState, useEffect } from 'react';
import axios from "axios";
import { Form, Button } from 'react-bootstrap';
import SelectReviewers from './SelectReviewers';
import "./css/CreatePost.scss";
import { useHistory } from "react-router-dom";
import * as urls from "./../URL"


function EditPostForm(props) {
    let history = useHistory();
    const [version, setVersion] = useState();
    const [reviewersIdsList, setReviewersIdsList] = useState([]);
    const [filePath, setFilePath] = useState();

    const [postToEdit, setPostToEdit] = useState({
        idOP: null,
        title: "",
        category: "",
        description: "",
        versions: [],
        reviewersIds: []
    });


    const [versionId, setVersionId] = useState();

    useEffect(() => {
        const userId = localStorage.getItem("user")
        //const userId = 1;
        getPostData();
        
    }, []);

    function getPostData() {
        axios.get(urls.baseURL + "post/" + props.location.state)
        .then((response) => {
            let size = response.data.versions.length;
            setFilePath(response.data.versions[size - 1].filePath);
            setPostToEdit(response.data);

            if(response.data.versions.length){
                setVersion(response.data.versions[0].versionCounter);
            }
        });
    }

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
    function updatePostInBE() {

        // check if URL valid
        if (!isValidHttpUrl(postToEdit.filePath)) {
        alert('The url is not valid. Please retry.');
        return false;
        }
        else{
        //   axios.post(urls.baseURL + 'post/create', post)
        //     .then((response) => {
        //       console.log(response);
        //     }, (error) => {
        //       console.log(error);
        //   });
        return true;
        }
    }

    const updatePost = e => {
        e.preventDefault();
        
        if(updatePostInBE() === true){
        //clear the state
        setPostToEdit({
            idOP: "",
            title: "",
            category: "",
            filePath: "",
            description: "",
            reviewersIds: []
        })
        // redirect to Home page
        history.push("/");
        }
    }

    //onchange method for textfields of post
    const changeHandler = e => {
        setPostToEdit({
        ...postToEdit,
            [e.target.name]: e.target.value })
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
        <Form onSubmit={updatePost} className="create-post-form">
            <Form.Group className="mb-3" controlId="title">
            <Form.Label className="create-post-lb">Title</Form.Label>
            <Form.Control size="lg" type="text" placeholder="Title" value={postToEdit.title} name="title" onChange={changeHandler} required />
            <Form.Text className="text-muted">
                Make sure to choose a descriptive title.
            </Form.Text>
            </Form.Group>
            <Form.Group className="mb-3" controlId="category">
            <Form.Label className="create-post-lb">Category</Form.Label>
            <Form.Select name="category" onChange={changeHandler} required>
                <option value={postToEdit.category}>{postToEdit.category}</option>
                <option value="Software">Software</option>
                <option value="Media">Media</option>
                <option value="Infrastructure">Infrastructure</option>
            </Form.Select>
            </Form.Group>
            <Form.Group className="mb-3" controlId="description">
            <Form.Label className="create-post-lb">Description</Form.Label>
            <Form.Control as="textarea" rows={5} value={postToEdit.description} name="description" onChange={changeHandler} required />
            </Form.Group>
            <Form.Group className="mb-3" controlId="filepath">
            <Form.Label className="create-post-lb">Paste file path here</Form.Label>
            <Form.Control size="lg" type="text" placeholder="Filepath" value={filePath} required />
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
