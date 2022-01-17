import React from 'react';
import Comments from './Comments';
import PostContent from './PostContent';
import VersionSelection from './VersionSelection';
import axios from 'axios';
import { Card, Button } from 'react-bootstrap';
import FilePreviewer from 'react-file-previewer';
import "./css/Post.scss";
import * as urls from "./../URL"
import { Link } from 'react-router-dom';
import DeleteIcon from '@material-ui/icons/Delete';
import toast, { Toaster } from 'react-hot-toast';



function Post(props) {
    const [post, setPost] = React.useState([]);
    const [version, setVersion] = React.useState();
    const [versionId, setVersionId] = React.useState();
    const [userID, setUserID] = React.useState();

    React.useEffect(() => {
        setTimeout(function () {
            setUserID(parseInt(localStorage.getItem("user")));
        }, 50);
        axios.get(urls.baseURL + "post/" + props.location.state)
            .then((response) => {
                setPost(null);
                setVersion(1);
                let size = response.data.versions.length;
                setPost(response.data);

                if (response.data.versions.length) {
                    setVersion(response.data.versions[0].versionCounter);
                    setVersionId(response.data.versions[0].versionId);
                }

                // changeVersionID(response.data);
            });
    }, []);

    React.useEffect(() => {
        if (version && post) {
            setVersionId(post.versions[version - 1].versionId);
        }
    }, [version]);

    if (!post) return null;
    if (!version) return null;


    function increaseVersion() {
        if (post.versions.length) {
            const highestVersion = post.versions.sort((b, a) => (a.versionId > b.versionId) ? 1 : ((b.versionId > a.versionId) ? -1 : 0));

            if (version < highestVersion[0].versionCounter) {
                setVersion(version + 1);
                console.log(version);
            }
        }

    }

    function decreaseVersion() {
        if (post.versions.length) {
            const lowestVersion = post.versions.sort((a, b) => (a.versionId > b.versionId) ? 1 : ((b.versionId > a.versionId) ? -1 : 0));
            if (version > lowestVersion[0].versionCounter) {
                setVersion(version - 1);
                console.log(version);
            }

        }
    }

    const handleDelete = (post) => {
    
        console.log(post)
        if (window.confirm('Are you sure to delete this post?')){
          axios.delete(`${urls.baseURL}post/${post.postId}`)
    
          toast("The post has been deleted")
          setTimeout(() => { window.location.href = "/"; }, 1000);
        }
      }

    return (
        <>
            <Card className="mt-1 mb-3 postBg">
                <Card.Body>
                    <div>
                        {post.idOP === userID &&
                            <Link to={{ pathname: "/fe/editPost", state: post.postId }}>
                                <Button className='editPostBtn'>Edit Post</Button>
                            </Link>
                        }
                    </div>
                    <div>
                        {post.idOP === userID &&
                            <Link to={{ pathname: "/fe/addVersion", state: post.postId }}>
                                <Button className='addNewVersionBtn mt-1'>Add new version</Button>
                            </Link>
                        }
                    </div>
                    <Card.Title><h1>{post.title}</h1></Card.Title>
                    <Card.Subtitle className="mb-2 text-muted">{post.postDate}</Card.Subtitle>
                    <p className="font-weight-light">{post.category}</p>
                    <div>
                        <DeleteIcon className="delete-icon" onClick={() => handleDelete(post)} />
                        <span className="delete-span" onClick={() => handleDelete(post)}>Delete post</span>
                    </div>
                    <hr className="pt-0 border-black rounded hrPostContent" />
                    <VersionSelection version={version} incr={increaseVersion} decr={decreaseVersion} />
                    <Card.Text>
                        <PostContent post={post} version={version} />
                    </Card.Text>
                </Card.Body>
            </Card>
            <Comments version={versionId} />
        </>

    );
}

export default Post;