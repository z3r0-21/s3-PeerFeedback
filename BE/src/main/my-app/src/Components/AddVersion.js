import axios from 'axios';
import React from 'react';
import { Button, Form } from 'react-bootstrap';
import { Redirect } from 'react-router-dom';
import { Link } from 'react-router-dom';
import * as urls from "./../URL"

function AddVersion(props) {
    const [version, setVersion] = React.useState({
        postId: props.location.state,
        filePath: ""
      });    
    const [redirect, setRedirect] = React.useState(false);

    const versionPOST = e => {
        e.preventDefault();

        if (!isValidHttpUrl(version.filePath)) {
          alert('The url is not valid. Please retry.');
        }
        else{
          axios.post(urls.baseURL + 'post/addVersion', version)
            .then((response) => {
                setRedirect(true);
            }, (error) => {
              console.log(error);
          });
        }
    }
    
    const changeHandler = e => {
        setVersion({
           ...version,
            [e.target.name]: e.target.value })

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

      if (redirect) {
        return <Redirect
            to={{
            pathname: "/fe/selectedPost",
            state: version.postId
            }}
        />
      }

    return (
            <>
            <Form className="create-post-form" onSubmit={versionPOST}>
                <Form.Group className="mb-3" controlId="filepath">
                <Form.Label className="create-post-lb">Paste file path here</Form.Label>
                <Form.Control size="lg" type="text" placeholder="Filepath" name="filePath" onChange={changeHandler} required />
                <Form.Text className="text-muted">
                    One drive url required
                </Form.Text>
                </Form.Group>
                {/* <Link to={{  pathname: "/fe/selectedPost", state: props.location.state }}> */}
                <Button variant="primary" type="submit">
                Submit new version
                </Button>
                {/* </Link> */}
            </Form>
            </>
    );
}

export default AddVersion;