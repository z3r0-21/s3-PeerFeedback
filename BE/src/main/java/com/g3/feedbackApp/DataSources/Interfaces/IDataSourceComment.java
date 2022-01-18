package com.g3.feedbackApp.DataSources.Interfaces;

import com.g3.feedbackApp.Models.CommentModel;

import java.util.List;

public interface IDataSourceComment {

    CommentModel createComment(CommentModel commentModel);
    CommentModel getCommentWithID(Long ID);
    List<CommentModel> getCommentsWithVersionID(Long ID);
    List<CommentModel> getComments();
    void deleteAllByVersionId(Long versionId);
}
