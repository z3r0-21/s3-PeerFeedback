package com.g3.feedbackApp.DataSources.Interfaces;

import com.g3.feedbackApp.Models.PostModel;
import com.g3.feedbackApp.Models.ReviewerModel;
import com.g3.feedbackApp.Models.VersionModel;

import java.util.List;

public interface IDataSourcePost {

    boolean createPost(PostModel postModel);

    boolean createVersion(int postId, String filePath);

    boolean assignReviewers(List<Integer> reviewersIds, int postId);

    PostModel getPostWithId(int postId);

    VersionModel getVersionWithId(int versionId);

    List<VersionModel> getVersionsForPost(int postId);

    List<Integer> getReviewersIdsForPost(int postId);


}
