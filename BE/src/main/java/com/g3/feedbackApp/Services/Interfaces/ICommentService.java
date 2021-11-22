package com.g3.feedbackApp.Services.Interfaces;

import com.g3.feedbackApp.Models.CommentModel;

import java.util.List;

public interface ICommentService {

    CommentModel createComment(CommentModel commentModel);
    List<CommentModel> getComments();
    CommentModel getCommentWithId(Long commentId);
    List<CommentModel> getCommentsWithVersionId(Long versionId);
}
