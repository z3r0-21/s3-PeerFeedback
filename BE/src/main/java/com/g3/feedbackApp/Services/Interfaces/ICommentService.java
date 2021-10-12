package com.g3.feedbackApp.Services.Interfaces;

import com.g3.feedbackApp.Models.CommentModel;

public interface ICommentService {

    boolean createComment(CommentModel commentModel);
}
