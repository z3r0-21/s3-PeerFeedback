import { TextareaAutosize } from '@material-ui/core';
import React from 'react';
import { Accordion } from 'react-bootstrap';

function PostContent({post, version}) {
    return (
        <div>
            <h6>Description</h6>
            <TextareaAutosize className="w-100 p-3 border rounded" disabled>{post.description}</TextareaAutosize>
            <br/>
            <span>File url: </span>
            <a href={post.versions[version - 1].filePath} target="_blank">URL to OneDrive</a>
            {/* <Accordion defaultActiveKey="0">
            <Accordion.Item eventKey="1">
                <Accordion.Header>Attached file(s)</Accordion.Header>
                <Accordion.Body style={{height: '50rem'}}>
                <iframe src="https://ihatetomatoes.net/wp-content/uploads/2017/01/react-cheat-sheet.pdf" width="100%" height="100%" />
                </Accordion.Body>
            </Accordion.Item>
            </Accordion> */}

        </div>
    );
}

export default PostContent;