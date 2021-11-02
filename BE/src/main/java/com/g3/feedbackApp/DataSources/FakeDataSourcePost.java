package com.g3.feedbackApp.DataSources;

import com.g3.feedbackApp.DataSources.Interfaces.IDataSourcePost;
import com.g3.feedbackApp.DataSources.Interfaces.IDataSourceReviewer;
import com.g3.feedbackApp.Models.ReviewerModel;
import com.g3.feedbackApp.Models.PostModel;
import com.g3.feedbackApp.Models.VersionModel;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class FakeDataSourcePost implements IDataSourcePost {

    private static int postIdCounter = 4;
    List<PostModel> postModelList = new ArrayList<>();

    private static int versionIdCounter = 4;
    List<VersionModel> versionModelList = new ArrayList<>();

    private static int reviewerConnIdCounter = 1;
    List<ReviewerModel> reviewerModelList = new ArrayList<>();

    private IDataSourceReviewer dataSourceReviewer = new FakeDataSourceReviewer();

    public FakeDataSourcePost(){
        postModelList.add(new PostModel(1, 1,"First Post", "code", "my first code post", LocalDate.now(), null));
        postModelList.add(new PostModel(2, 1,"Second Post", "Assay", "Please check my assay", LocalDate.now(), null));
        postModelList.add(new PostModel(3, 1,"Third Post", "DB Diagrams", "The first draft", LocalDate.now(), null));

        versionModelList.add(new VersionModel(1, 1, "testFilePathVersion1"));
        versionModelList.add(new VersionModel(2, 1, "testFilePathVersion2"));
        versionModelList.add(new VersionModel(3, 1, "testFilePathVersion3"));

//        reviewerModelList.add(new ReviewerModel(1, 1, 2));
//        reviewerModelList.add(new ReviewerModel(2, 2, 2));
//        reviewerModelList.add(new ReviewerModel(3, 3, 2));


    }

    @Override
    public boolean createPost(PostModel postModel) {
        postModel.setPostId(postIdCounter);
        postModel.setPostDate(LocalDate.now());
        postModelList.add(postModel);
        postIdCounter++;
        return true;
    }

    @Override
    public boolean createVersion(int postId, String filePath) {
        versionModelList.add(new VersionModel(versionIdCounter, postId, filePath));
        versionIdCounter++;
        return true;
    }

    @Override
    public boolean assignReviewers(List<Integer> reviewersIds, int postId) {
        for(int id: reviewersIds){
            reviewerModelList.add(new ReviewerModel(reviewerConnIdCounter, postId, id));
            reviewerConnIdCounter++;
        }
        return true;
    }

    @Override
    public PostModel getPostWithId(int Id) {
        for(PostModel p: postModelList){
            if(p.getPostId() == Id){
                return p;
            }
        }
        return null;
    }

    @Override
    public VersionModel getVersionWithId(int versionId) {
        for(VersionModel v: versionModelList){
            if(v.getVersionId() == versionId){
                return v;
            }
        }
        return null;
    }

    @Override
    public List<VersionModel> getVersionsForPost(int postId) {
        List<VersionModel> listOfIds = new ArrayList<>();
        for(VersionModel v: versionModelList){
            if(v.getPostId() == postId){
                listOfIds.add(v);
            }
        }
        return listOfIds;
    }

    @Override
    public List<Integer> getReviewersIdsForPost(int postId) {
        List<Integer> listOfIds = new ArrayList<>();
        for(ReviewerModel reviewer: reviewerModelList){
            if(reviewer.getPostId() == postId){
                listOfIds.add(reviewer.getId());
            }
        }
        return listOfIds;
    }
}
