package com.g3.feedbackApp.Services;

import com.g3.feedbackApp.DataSources.Interfaces.IDataSourceComment;
import com.g3.feedbackApp.DataSources.Interfaces.IDataSourcePost;
import com.g3.feedbackApp.Models.CommentModel;
import com.g3.feedbackApp.Services.Interfaces.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommentService implements ICommentService{

    private IDataSourceComment datasource;

    private IDataSourcePost userDataSource;

    public CommentService(IDataSourceComment datasource, IDataSourcePost userDataSource) {
        this.datasource = datasource;
        this.userDataSource = userDataSource;
    }

    @Override
    public CommentModel createComment(CommentModel commentModel) {
        return (datasource.createComment(commentModel));
    }

    @Override
    public CommentModel getCommentWithId(Long commentId){
        return datasource.getCommentWithID(commentId);
    }

    @Override
    public List<CommentModel> getCommentsWithVersionId(Long versionId){
        return datasource.getCommentsWithVersionID(versionId);
    }

    @Override
    public List<CommentModel> getComments(){
        return datasource.getComments();
    }
}
