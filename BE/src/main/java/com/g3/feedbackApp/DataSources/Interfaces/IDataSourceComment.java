package com.g3.feedbackApp.DataSources.Interfaces;

import com.g3.feedbackApp.Models.CommentModel;

public interface IDataSourceComment {

    public boolean createComment();
    public CommentModel getCommentWithID(int ID);
}
