import React from 'react';
import Comments from './Comments';
import PostContent from './PostContent';
import VersionSelection from './VersionSelection';
import axios from 'axios';
import { Card } from 'react-bootstrap';
import FilePreviewer from 'react-file-previewer';



function Post({postId}) {
    const [post, setPost] = React.useState([]);
    const [version, setVersion] = React.useState();

    React.useEffect(() => {
        axios.get("http://localhost:8080/post/1")
        .then((response) => {
            setPost(null);
            setVersion(-1);
            setPost(response.data);

            if(response.data.versions.length){
                setVersion(response.data.versions[0].versionId);
            }
            else{
                setVersion(1);
            }
        });
    }, []);

    if (!post) return null;
    if (!version) return null;

    function increaseVersion(){
        if(post.versions.length){
            const highestVersion = post.versions.sort((b,a) => (a.versionId > b.versionId) ? 1 : ((b.versionId > a.versionId) ? -1 : 0));

            if(version < highestVersion[0].versionId){
                setVersion(version + 1);
        }
        }
        
    }

    function decreaseVersion(){
        if(post.versions.length){
            const lowestVersion = post.versions.sort((a,b) => (a.versionId > b.versionId) ? 1 : ((b.versionId > a.versionId) ? -1 : 0));

            if(version > lowestVersion[0].versionId){
                setVersion(version - 1);
            }
        }
        
    }

    return (
        <>
        <Card className="mt-1 mb-3" style={{background: "#d4f8ff"  }}>
        <Card.Body>
            <Card.Title><h1>{post.title}</h1></Card.Title>
            <Card.Subtitle className="mb-2 text-muted">{post.postDate}</Card.Subtitle>
            <p className="font-weight-light">{post.category}</p>
            <hr className="pt-0 border rounded"/>
            <VersionSelection version={version} incr={increaseVersion} decr={decreaseVersion}/>
            <Card.Text>
            <PostContent post = {post}/>
            </Card.Text>
        </Card.Body>
        </Card>
        <Comments />
        </>
        
    );
}

export default Post;