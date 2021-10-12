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
}
