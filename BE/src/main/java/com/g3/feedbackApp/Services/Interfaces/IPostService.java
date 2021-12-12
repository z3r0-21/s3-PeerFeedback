package com.g3.feedbackApp.Services.Interfaces;

import com.g3.feedbackApp.Models.PostModel;
import com.g3.feedbackApp.Models.VersionModel;

import java.nio.file.Path;
import java.util.List;

public interface IPostService {

    Boolean createPost(PostModel postModel, String filePath, List<Long> reviewersIds);
    Boolean createVersion(Long postId, String filePath);
    PostModel getPostWithId(Long postId);
    List<PostModel> getPostsToReview(Long reviewerId);
    List<PostModel> getMyPosts(Long idOP);
    List<PostModel> getAllPosts();
    VersionModel getVersionWithId(Long versionId);
    List<VersionModel> getVersionsForPost(Long postId);
    List<Long> getReviewersIdsForPost(int postId);

}
