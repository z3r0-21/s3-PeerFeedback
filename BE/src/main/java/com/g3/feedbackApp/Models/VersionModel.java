package com.g3.feedbackApp.Models;

import java.nio.file.Path;

public class VersionModel {

    private int versionId;
    private int postId;
    private Path filePath;

    public VersionModel(int versionId, int postId, Path filePath){
        this.versionId = versionId;
        this.postId = postId;
        this.filePath = filePath;
    }

    public void setVersionId(int versionId) {
        this.versionId = versionId;
    }
    public int getVersionId(){return this.versionId;}

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getPostId() {
        return postId;
    }

    public void setFilePath(Path filePath) {
        this.filePath = filePath;
    }

    public Path getFilePath() {
        return filePath;
    }
}
