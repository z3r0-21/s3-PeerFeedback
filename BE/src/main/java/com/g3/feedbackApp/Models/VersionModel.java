package com.g3.feedbackApp.Models;

public class VersionModel {

    private int versionId;
    private int postId;
    private String filePath;

    public VersionModel(int versionId, int postId, String filePath){
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

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }
}
