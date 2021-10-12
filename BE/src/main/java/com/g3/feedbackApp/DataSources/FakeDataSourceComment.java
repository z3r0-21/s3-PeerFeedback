package com.g3.feedbackApp.DataSources;

import com.g3.feedbackApp.DataSources.Interfaces.IDataSourceComment;
import com.g3.feedbackApp.Models.CommentModel;
import org.springframework.stereotype.Component;

@Component
public class FakeDataSourceComment implements IDataSourceComment {
    @Override
    public boolean createComment() {
        return false;
    }

    @Override
    public CommentModel getCommentWithID(int ID) { return null; }
}
