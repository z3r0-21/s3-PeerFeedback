package com.g3.feedbackApp.DataSources;

import com.g3.feedbackApp.DataSources.Interfaces.IDataSourcePost;
import com.g3.feedbackApp.Models.PostModel;
import org.springframework.stereotype.Component;

@Component
public class FakeDataSourcePost implements IDataSourcePost {
    @Override
    public boolean createPost() {
        return false;
    }

    @Override
    public PostModel getPostWithID(int ID) {
        return null;
    }
}
