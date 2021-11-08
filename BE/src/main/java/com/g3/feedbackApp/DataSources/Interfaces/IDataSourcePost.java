package com.g3.feedbackApp.DataSources.Interfaces;

import com.g3.feedbackApp.Models.PostModel;
import com.g3.feedbackApp.Models.VersionModel;

import java.nio.file.Path;
import java.util.List;

public interface IDataSourcePost {

    boolean createPost(PostModel postModel);

    boolean createVersion(Long postId, Path filePath);

    boolean assignReviewers(List<Long> reviewersIds, Long postId);

    PostModel getPostWithId(Long postId);

    VersionModel getVersionWithId(Long versionId);

    List<VersionModel> getVersionsForPost(Long postId);

    List<Long> getReviewersIdsForPost(int postId);


}
