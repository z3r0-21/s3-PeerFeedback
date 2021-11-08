package com.g3.feedbackApp.DataSources;

import com.g3.feedbackApp.DataSources.Interfaces.IDataSourcePost;
import com.g3.feedbackApp.DataSources.Interfaces.IDataSourceReviewer;
import com.g3.feedbackApp.Models.ReviewerModel;
import com.g3.feedbackApp.Models.PostModel;
import com.g3.feedbackApp.Models.VersionModel;
import org.springframework.stereotype.Repository;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class FakeDataSourcePost implements IDataSourcePost {

    private static Long postIdCounter = 4L;
    List<PostModel> postModelList = new ArrayList<>();

    private static Long versionIdCounter = 4L;
    List<VersionModel> versionModelList = new ArrayList<>();

    private static Long reviewerConnIdCounter = 1L;
    List<ReviewerModel> reviewerModelList = new ArrayList<>();

    private IDataSourceReviewer dataSourceReviewer = new FakeDataSourceReviewer();

    public FakeDataSourcePost(){
        postModelList.add(new PostModel(1L, 1,"First Post", "code", "my first code post", LocalDate.now(), null));
        postModelList.add(new PostModel(2L, 1,"Second Post", "Assay", "Please check my assay", LocalDate.now(), null));
        postModelList.add(new PostModel(3L, 1,"Third Post", "DB Diagrams", "The first draft", LocalDate.now(), null));

        versionModelList.add(new VersionModel(1L, 1L, Path.of("testFilePathVersion1")));
        versionModelList.add(new VersionModel(2L, 1L, Path.of("testFilePathVersion2")));
        versionModelList.add(new VersionModel(3L, 1L, Path.of("testFilePathVersion3")));

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
    public boolean createVersion(Long postId, Path filePath) {
        versionModelList.add(new VersionModel(versionIdCounter, postId, filePath));
        versionIdCounter++;
        return true;
    }

    @Override
    public boolean assignReviewers(List<Long> reviewersIds, Long postId) {
        for(Long id: reviewersIds){
            reviewerModelList.add(new ReviewerModel(reviewerConnIdCounter, postId, id.intValue()));
            reviewerConnIdCounter++;
        }
        return true;
    }

    @Override
    public PostModel getPostWithId(Long Id) {
        for(PostModel p: postModelList){
            if(p.getPostId() == Id){
                return p;
            }
        }
        return null;
    }

    @Override
    public VersionModel getVersionWithId(Long versionId) {
        for(VersionModel v: versionModelList){
            if(v.getVersionId() == versionId){
                return v;
            }
        }
        return null;
    }

    @Override
    public List<VersionModel> getVersionsForPost(Long postId) {
        List<VersionModel> listOfIds = new ArrayList<>();
        for(VersionModel v: versionModelList){
            if(v.getPostId() == postId){
                listOfIds.add(v);
            }
        }
        return listOfIds;
    }

    @Override
    public List<Long> getReviewersIdsForPost(int postId) {
        List<Long> listOfIds = new ArrayList<>();
        for(ReviewerModel reviewer: reviewerModelList){
            if(reviewer.getPostId() == postId){
                listOfIds.add(reviewer.getId());
            }
        }
        return listOfIds;
    }
}
