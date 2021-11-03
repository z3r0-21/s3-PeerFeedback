package com.g3.feedbackApp.Services;

import com.g3.feedbackApp.DataSources.Interfaces.IDataSourcePost;
import com.g3.feedbackApp.Models.PostModel;
import com.g3.feedbackApp.Models.VersionModel;
import com.g3.feedbackApp.Services.Interfaces.IPostService;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.util.List;

@Component
public class PostService implements IPostService {

    private IDataSourcePost datasource;

    public PostService(IDataSourcePost datasource) {
        this.datasource = datasource;
    }

    @Override
    public Boolean createPost(PostModel postModel, Path filePath, List<Integer> reviewersIds) {
        return datasource.createPost(postModel) && datasource.createVersion(postModel.getPostId(), filePath) && datasource.assignReviewers(reviewersIds, postModel.getPostId());
    }

    @Override
    public PostModel getPostWithId(int id) {
        return datasource.getPostWithId(id);
    }

    @Override
    public VersionModel getVersionWithId(int versionId) {
        return datasource.getVersionWithId(versionId);
    }

    @Override
    public List<VersionModel> getVersionsForPost(int postId) {
        return datasource.getVersionsForPost(postId);
    }

    @Override
    public List<Integer> getReviewersIdsForPost(int postId) {
        return datasource.getReviewersIdsForPost(postId);
    }


}
