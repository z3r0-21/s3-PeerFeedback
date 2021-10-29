import React, {useState, useEffect} from 'react';
import {Form, Button, InputGroup, FormControl} from "react-bootstrap";
import 'bootstrap/dist/css/bootstrap.min.css';
import SelectedReviewer from './SelectedReviewer';
import axios from 'axios';

const client = axios.create({
    baseURL: "http://localhost:8080/" 
});


function SelectReviewers() {
    const [started, setStarted] = useState(false);
    const [users, setUsers] = useState([]);

    const [input, setInput] = useState("");
    const [checkAddReviewer, setCheckAddReviewer] = useState("");
    const [searchResults, setSearchResults] = useState([]);
    const [reviewers, setReviewers] = useState([]);

    const handleInputChange = (e) => {
        var inputVal = e.target.value;
        setInput(inputVal);
    }

    useEffect(() => {
        retrieveUsers();
        console.log("here");
    }, []);

    useEffect(() => {
        console.log(users);
        const results = users.filter(user =>
          user.email.split("@")[0].toLowerCase().includes(input)
        );
        if(input !== ""){
            setSearchResults(results);
        }else{
            setSearchResults([]);
        }

    }, [input]);

    async function retrieveUsers() {
        const response = await client.get("/users");
        setUsers(response.data);
    }

    const handleClick = (user) => {
        setReviewers([...reviewers, user]);
        
        setUsers([
            ...users.filter(u => {
                return u.studentNr !== user.studentNr
            })
        ]);
        setInput("");
    }

    const handleRemoveReviewer = (reviewer) => {
        // Remove the chosen reviewer from the reviewer list
        setReviewers([
            ...reviewers.filter(r => {
                return r.studentNr !== reviewer.studentNr
            })
        ]);
        // Add removed reviewer to people list
        setUsers([...users, reviewer]);
    }

    const saveReviewers = (e) => {
        e.preventDefault();
        console.log("save reviewers");
    }

    return (
        <>
        {users.map((u) => (
            <div>{u.email}</div>
        ))}
         <Form autoComplete="off" onSubmit={saveReviewers}>
            <Form.Group className="mb-3" controlId="formBasicEmail">
                <Form.Label>Add searchResults</Form.Label>
                <InputGroup className="mb-3">
                    <FormControl
                    
                    placeholder="Select searchResults..."
                    aria-label="Select searchResults..."
                    aria-describedby="basic-addon2"
                    onChange={handleInputChange}
                    value={input}
                    />
                    <InputGroup.Text id="basic-addon2">@student.fontys.nl</InputGroup.Text>
                </InputGroup>
            </Form.Group>
            {searchResults.map((user) => (
                <div key={user.studentNr} className="searchResults">
                    <div onClick={() => handleClick(user)}>{user.studentNr} {user.email}</div>
                </div>
            ))}

            <Button variant="primary" type="submit">
                Submit
            </Button>
        </Form>   
        {reviewers.map((reviewer) => (
            <div key={reviewer.studentNr} className="reviewers">
                <SelectedReviewer reviewer={reviewer} handleRemoveReviewer={handleRemoveReviewer}/>
            </div>
        ))}

        </>
    );
}

export default SelectReviewers;