package com.g3.feedbackApp.DataSources.Interfaces;

import com.g3.feedbackApp.Models.PostModel;

public interface IDataSourcePost {

    public boolean createPost();
    public PostModel getPostWithID(int ID);
}
