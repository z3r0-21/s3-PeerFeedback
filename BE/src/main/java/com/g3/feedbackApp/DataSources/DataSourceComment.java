package com.g3.feedbackApp.DataSources;

import com.g3.feedbackApp.DataSources.Interfaces.IDataSourceComment;
import com.g3.feedbackApp.Models.CommentModel;
import com.g3.feedbackApp.Repository.ICommentRepository;
import com.g3.feedbackApp.Repository.IVersionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public class DataSourceComment implements IDataSourceComment {

    @Autowired
    ICommentRepository commentRepository;

    @Autowired
    IVersionRepository versionRepository;

    @Override
    public CommentModel createComment(CommentModel commentModel) {
        if(versionRepository.getFirstByVersionId(commentModel.getVersionId()) != null && !Objects.equals(commentModel.getText(), "")){
            return commentRepository.save(commentModel);
        }
        return null;
    }

    @Override
    public CommentModel getCommentWithID(Long id) {
        return commentRepository.getFirstByCommentId(id);
    }

    @Override
    public List<CommentModel> getCommentsWithVersionID(Long id) {
        return commentRepository.getCommentModelsByVersionId(id);
    }

    @Override
    public List<CommentModel> getComments() {
        return commentRepository.findAll();
    }
}
