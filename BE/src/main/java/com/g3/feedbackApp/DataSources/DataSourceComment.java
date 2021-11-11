package com.g3.feedbackApp.DataSources;

import com.g3.feedbackApp.DataSources.Interfaces.IDataSourceComment;
import com.g3.feedbackApp.Models.CommentModel;
import com.g3.feedbackApp.Repository.ICommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DataSourceComment implements IDataSourceComment {

    @Autowired
    ICommentRepository commentRepository;

    @Override
    public boolean createComment(CommentModel commentModel) {
        commentRepository.save(commentModel);
        return true;
    }

    @Override
    public CommentModel getCommentWithID(int id) {
        return commentRepository.getFirstByCommentId(id);
    }

    @Override
    public List<CommentModel> getCommentsWithVersionID(int id) {
        return commentRepository.getCommentModelsByVersionId(id);
    }

    @Override
    public List<CommentModel> getComments() {
        return commentRepository.findAll();
    }
}
