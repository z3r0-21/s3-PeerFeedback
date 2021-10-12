package com.g3.feedbackApp.DataSources;

import com.g3.feedbackApp.DataSources.Interfaces.IDataSourcePost;
import com.g3.feedbackApp.Models.PostModel;
import com.g3.feedbackApp.Models.VersionModel;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class FakeDataSourcePost implements IDataSourcePost {

    private static int postIdCounter = 4;
    List<PostModel> postModelList = new ArrayList<>();

    private static int versionIdCounter = 1;
    List<VersionModel> versionModelList = new ArrayList<>();

    public FakeDataSourcePost(){
        postModelList.add(new PostModel(1, 1,"First Post", "code", "my first code post", LocalDate.now(), null));
        postModelList.add(new PostModel(2, 1,"Second Post", "Assay", "Please check my assay", LocalDate.now(), null));
        postModelList.add(new PostModel(3, 1,"Third Post", "DB Diagrams", "The first draft", LocalDate.now(), null));
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
        return true;
    }

    @Override
    public PostModel getPostWithID(int ID) {
        return null;
    }
}
