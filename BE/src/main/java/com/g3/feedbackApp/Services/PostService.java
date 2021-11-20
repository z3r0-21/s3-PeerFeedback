package com.g3.feedbackApp.Services;

import com.g3.feedbackApp.DataSources.Interfaces.IDataSourcePost;
import com.g3.feedbackApp.DataSources.Interfaces.IDataSourceReviewer;
import com.g3.feedbackApp.Models.PostModel;
import com.g3.feedbackApp.Models.ReviewerModel;
import com.g3.feedbackApp.Models.VersionModel;
import com.g3.feedbackApp.Services.Interfaces.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class PostService implements IPostService {

    @Autowired
    private IDataSourcePost datasource;

    @Autowired
    private IDataSourceReviewer dataSourceReviewers;

    @Override
    public Boolean createPost(PostModel postModel, Path filePath, List<Long> reviewersIds) {
        //Check if object holds value
        if(!Objects.equals(postModel.getTitle(), "")){
            return datasource.createPost(postModel) && createVersion(postModel.getPostId(), filePath) && datasource.assignReviewers(reviewersIds, postModel.getPostId());
        }
        return false;
    }

    @Override
    public Boolean createVersion(Long postId, Path filePath) {
        return datasource.createVersion(postId, filePath);
    }


    @Override
    public PostModel getPostWithId(Long id) {
        return datasource.getPostWithId(id);
    }

    @Override
    public List<PostModel> getPostsToReview(Long userId) {
        List<ReviewerModel> reviewersWithUserId =  dataSourceReviewers.getReviewers().
                stream().
                filter(reviewerModel -> reviewerModel.getUserId() == userId).
                collect(Collectors.toList());
        List<PostModel> postsToReview = new ArrayList<>();
        for (ReviewerModel reviewer : reviewersWithUserId) {
            for (PostModel post : getAllPosts()) {
                if(post.getPostId() == reviewer.getPostId())
                {
                    postsToReview.add(post);
                }
            }
        }
        return postsToReview;
    }

    @Override
    public List<PostModel> getMyPosts(Long idOP) {
        return getAllPosts().
                stream().
                filter(postModel -> postModel.getIdOP()==idOP).
                collect(Collectors.toList());
    }

    @Override
    public List<PostModel> getAllPosts() {
        return datasource.getAllPosts();
    }

    @Override
    public VersionModel getVersionWithId(Long versionId) {
        return datasource.getVersionWithId(versionId);
    }

    @Override
    public List<VersionModel> getVersionsForPost(Long postId) {
        return datasource.getVersionsForPost(postId);
    }

    @Override
    public List<Long> getReviewersIdsForPost(int postId) {
        return datasource.getReviewersIdsForPost(postId);
    }


}
