package com.g3.feedbackApp.Services.Interfaces;

import com.g3.feedbackApp.Models.CommentModel;

import java.util.List;

public interface ICommentService {

    boolean createComment(CommentModel commentModel);
    List<CommentModel> getComments();
    CommentModel getCommentWithId(int commentId);
    List<CommentModel> getCommentsWithVersionId(int versionId);
}
