package com.g3.feedbackApp.Services.Interfaces;

import com.g3.feedbackApp.Models.PostModel;
import com.g3.feedbackApp.Models.VersionModel;

import java.nio.file.Path;
import java.util.List;

public interface IPostService {

    Boolean createPost(PostModel postModel, Path filePath, List<Long> reviewersIds);
    PostModel getPostWithId(Long postId);
    VersionModel getVersionWithId(Long versionId);
    List<VersionModel> getVersionsForPost(Long postId);
    List<Long> getReviewersIdsForPost(int postId);

}
