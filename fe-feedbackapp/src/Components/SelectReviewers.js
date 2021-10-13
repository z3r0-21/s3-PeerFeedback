import React, {useState, useCallback} from 'react';
import {Form, Button, InputGroup, FormControl} from "react-bootstrap";
import 'bootstrap/dist/css/bootstrap.min.css';
import SelectedReviewer from './SelectedReviewer';



function SelectReviewers() {
    const [people, setPeople] = useState([
        {
          id: 0,
          email: "john.peter@student.fontys.nl",
        },
        {
          id: 1,
          email: "peter.eirk@student.fontys.nl",
        },
        {
          id: 2,
          email: "bob@student.fontys.nl",
        }
    ]);

    const [input, setInput] = useState("");
    const [searchResults, setsearchResults] = useState([]);
    const [reviewers, setReviewers] = useState([]);

    

    const handleInputChange = (e) => {
        //console.log(e.target.value);
        var inputVal = e.target.value;
        setInput(inputVal);
        //console.log(input);
        //setsearchResults(filterPeople(input));
    }

    React.useEffect(() => {
        const results = people.filter(person =>
          person.email.split("@")[0].toLowerCase().includes(input)
        );
        if(input != ""){
            setsearchResults(results);
        }else{
            setsearchResults([]);
        }

      }, [input]);

    const filterPeople = (input) => {
        let peopleEmailBeforePrefix = people.map((person) => (person.email.split("@")[0]));
        
        return peopleEmailBeforePrefix.filter(function (person) {
            return person.toLowerCase().includes(input.toLowerCase());
          })
    }
    
    const handleClick = (person) => {
        //console.log(person.id);
        
        setReviewers([...reviewers, person]);
        setPeople([
            ...people.filter(p => {
                return p.id !== person.id
            })
        ])
        
        setInput("");
        //console.log(people);

    }

    const handleRemoveReviewer = (reviewer) => {
        // Remove the chosen reviewer from the reviewer list
        setReviewers([
            ...reviewers.filter(r => {
                return r.id !== reviewer.id
            })
        ])
        // Add removed reviewer to people list
        setPeople([...people, reviewer]);
    }

    return (
        <>
         <Form autoComplete="off">
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
            {searchResults.map((person) => (
                <div key={person.id} className="searchResults">
                    <div onClick={() => handleClick(person)}>{person.id} {person.email}</div>
                </div>
            ))}

            <Button variant="primary" type="submit">
                Submit
            </Button>
        </Form>   
        {reviewers.map((reviewer) => (
            <div key={reviewer.id} className="reviewers">
                <SelectedReviewer reviewer={reviewer} handleRemoveReviewer={handleRemoveReviewer}/>
            </div>
        ))}

        </>
    );
}

export default SelectReviewers;