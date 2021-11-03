import React from "react"
import Comments from "./Comments"
import ReplyBox from "./ReplyBox"

const CommentSection = () => {
    return (
        <>
            <div className='comment-section'>
                <div className='comments'>
                    <Comments />
                </div>
                <div className='reply-box'>
                    <ReplyBox />
                </div>
            </div>

        </>
    )
}

export default CommentSection