import React, { useState, useEffect } from 'react';
import axios from "axios";
import { Form, Button } from 'react-bootstrap';
import SelectReviewers from './SelectReviewers';
import "./css/CreatePost.scss";
import { useHistory } from "react-router-dom";
import * as urls from "./../URL"
import toast, { Toaster } from 'react-hot-toast';


function EditPostForm(props) {
    let history = useHistory();
    const [reviewersIdsList, setReviewersIdsList] = useState([]);

    const [postToEdit, setPostToEdit] = useState({
        postId: null,
        title: "",
        category: "",
        description: "",
        reviewersIds: []
    });

    useEffect(() => {
        const userId = localStorage.getItem("user")
        //const userId = 1;
        getPostData();

    }, []);

    function getPostData() {
        axios.get(urls.baseURL + "post/" + props.location.state)
            .then((response) => {
                setPostToEdit(response.data);
            });
    }

    async function updatePost(e) {
        e.preventDefault();
        toast.loading("Your request is being processed", {
            duration: 2000
        })

        const postToEditState = {
            postId: postToEdit.postId,
            title: postToEdit.title,
            category: postToEdit.category,
            description: postToEdit.description,
            reviewersIds: reviewersIdsList
        }

        //update post here 
        await axios.put(urls.baseURL + 'post/update', postToEditState)
            .then(response => {
                console.log(response);
                toast.success("Your update is processed successfuly",{
                    duration: 5000
                  })
            }).catch(error => {
                if (error != null) {
                    alert("Something when wrong while updating your post. " + error)
                }
                else {
                    alert("Something went wrong while updating your post!")
                    return false
                }

            });

        history.push({ pathname: "/", state: { updatePost: postToEdit } });
        //clear the state
        setPostToEdit({
            postId: null,
            title: "",
            category: "",
            filePath: "",
            description: "",
            reviewersIds: []
        })
    }

    //onchange method for textfields of post
    const changeHandler = e => {
        setPostToEdit({
            ...postToEdit,
            [e.target.name]: e.target.value
        })
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
            <div><Toaster /></div>
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
                <SelectReviewers postId={props.location.state} addReviewerId={addReviewerId} removeReviewerId={removeReviewerId} />
                <Button variant="primary" type="submit">
                    Submit
                </Button>
            </Form>
        </>
    );
}

export default EditPostForm;
