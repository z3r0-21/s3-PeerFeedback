package com.g3.feedbackApp.Services;

import com.g3.feedbackApp.DataSources.Interfaces.IDataSourceComment;
import com.g3.feedbackApp.Models.CommentModel;
import com.g3.feedbackApp.Services.Interfaces.ICommentService;
import org.springframework.stereotype.Component;

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

}
