package com.g3.feedbackApp.DataSources;

import com.g3.feedbackApp.DataSources.Interfaces.IDataSourcePost;
import com.g3.feedbackApp.DataSources.Interfaces.IDataSourceReviewer;
import com.g3.feedbackApp.Models.PostModel;
import com.g3.feedbackApp.Models.ReviewerModel;
import com.g3.feedbackApp.Models.VersionModel;
import com.g3.feedbackApp.Repository.IPostRepository;
import com.g3.feedbackApp.Repository.IReviewerRepository;
import com.g3.feedbackApp.Repository.IVersionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DataSourcePost implements IDataSourcePost {

    @Autowired
    IPostRepository postRepository;

    @Autowired
    IVersionRepository versionRepository;

    @Autowired
    IReviewerRepository reviewerRepository;


    @Override
    public boolean createPost(PostModel postModel) {
        postModel.setPostDate(LocalDate.now());
        postRepository.save(postModel);
        return true;
    }

    @Override
    public boolean createVersion(Long versionId, Long postId, String filePath) {
        versionRepository.save(new VersionModel(versionId, postId, filePath));
        return true;
    }

    @Override
    public boolean assignReviewers(List<Long> reviewersIds, Long postId) {
        for(Long id: reviewersIds){
            reviewerRepository.save(new ReviewerModel(postId, id));
        }
        return true;
    }

    @Override
    public PostModel getPostWithId(Long Id) {
        return postRepository.getFirstByPostId(Id);
    }

    @Override
    public List<PostModel> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public VersionModel getVersionWithId(Long versionId) {
        return versionRepository.getFirstByVersionId(versionId);
    }

    @Override
    public List<VersionModel> getVersionsForPost(Long postId) {
        return versionRepository.getVersionModelsByPostId(postId);
    }

    @Override
    public List<Long> getReviewersIdsForPost(int postId) {
        List<ReviewerModel> reviewers = reviewerRepository.getReviewerModelsByPostId((long) postId);
        List<Long> ids = new ArrayList<>();
        for(ReviewerModel reviewerModel: reviewers){
            ids.add(reviewerModel.getUserId());
        }
        return ids;
    }
}
