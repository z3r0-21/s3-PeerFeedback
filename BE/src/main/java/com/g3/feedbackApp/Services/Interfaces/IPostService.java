package com.g3.feedbackApp.Services.Interfaces;

import com.g3.feedbackApp.Models.PostModel;

public interface IPostService {

    Boolean createPost(PostModel postModel, String filePath);
}
