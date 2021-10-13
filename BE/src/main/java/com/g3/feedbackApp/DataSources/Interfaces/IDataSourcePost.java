package com.g3.feedbackApp.DataSources.Interfaces;

import com.g3.feedbackApp.Models.PostModel;
import com.g3.feedbackApp.Models.VersionModel;

import java.util.List;

public interface IDataSourcePost {

    public boolean createPost(PostModel postModel);
    public boolean createVersion(int postId, String filePath);
    public PostModel getPostWithId(int postId);
    public VersionModel getVersionWithId(int versionId);
    public List<VersionModel> getVersionsForPost(int postId);
}
