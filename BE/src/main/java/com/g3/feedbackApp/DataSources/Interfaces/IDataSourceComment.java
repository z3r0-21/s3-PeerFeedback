package com.g3.feedbackApp.DataSources.Interfaces;

import com.g3.feedbackApp.Models.CommentModel;

import java.util.List;

public interface IDataSourceComment {

    boolean createComment(CommentModel commentModel);
    CommentModel getCommentWithID(int ID);
    List<CommentModel> getCommentsWithVersionID(int ID);
    List<CommentModel> getComments();
}
