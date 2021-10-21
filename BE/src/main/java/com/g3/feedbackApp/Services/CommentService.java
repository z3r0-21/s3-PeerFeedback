package com.g3.feedbackApp.Services;

import com.g3.feedbackApp.DataSources.Interfaces.IDataSourceComment;
import com.g3.feedbackApp.Models.CommentModel;
import com.g3.feedbackApp.Services.Interfaces.ICommentService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommentService implements ICommentService{

    private IDataSourceComment datasource;

    public CommentService(IDataSourceComment datasource){
        this.datasource = datasource;
    }

    @Override
    public boolean createComment(CommentModel commentModel) {
        if(datasource.createComment(commentModel)){
            return true;
        }
        return false;
    }

    @Override
    public CommentModel getCommentWithId(int commentId){
        return datasource.getCommentWithID(commentId);
    }

    @Override
    public List<CommentModel> getCommentsWithVersionId(int versionId){
        return datasource.getCommentsWithVersionID(versionId);
    }

    @Override
    public List<CommentModel> getComments(){
        return datasource.getComments();
    }
}
