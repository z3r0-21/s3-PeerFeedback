import React, { Component } from 'react'
import axios from 'axios'

class Comments extends Component {

  state = {
    comments: []
}

        componentDidMount(){
        axios.get(`http://localhost:8080/comments`).then(res => {
        const comments = res.data;
        this.setState({ comments });
      });
        }




  render() {
    return (
         <ul className="collection">
            {this.state.comments.map(comment => (
              <li
                key={comment.commentId}
                className="collection-item left-align purple lighten-2"
              >
                <p>User ID: {comment.commentId}</p>
                <p>Feedback: {comment.text}</p>
              </li>
            ))}
          </ul>
    )
  }
}

export default Comments