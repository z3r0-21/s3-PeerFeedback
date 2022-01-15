package com.g3.feedbackApp.DataSources.Interfaces;

import com.g3.feedbackApp.Models.PostModel;
import com.g3.feedbackApp.Models.ReviewerModel;
import com.g3.feedbackApp.Models.VersionModel;

import java.nio.file.Path;
import java.util.List;

public interface IDataSourcePost {

    boolean createPost(PostModel postModel);

    PostModel updatePost(PostModel postModel);

    boolean createVersion(Long versionId, Long postId, String fileString);

    boolean assignReviewers(List<Long> reviewersIds, Long postId);

    void removeAllReviewers(Long postId);

    PostModel getPostWithId(Long postId);

    List<PostModel> getAllPosts();

    VersionModel getVersionWithId(Long versionId);

    List<VersionModel> getVersionsForPost(Long postId);

    List<Long> getReviewersIdsForPost(int postId);
    List<ReviewerModel> getReviewersForPost(int postId);

    boolean deletePostModel(Long postId);
}
