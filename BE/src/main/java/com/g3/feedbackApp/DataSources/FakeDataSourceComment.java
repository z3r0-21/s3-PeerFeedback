package com.g3.feedbackApp.DataSources;

import com.g3.feedbackApp.DataSources.Interfaces.IDataSourceComment;
import com.g3.feedbackApp.Models.CommentModel;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;

@Component
public class FakeDataSourceComment implements IDataSourceComment {


    private static int commentIdCounter = 3;
    List<CommentModel> commentModelList = new ArrayList<>();

    public FakeDataSourceComment(){
        commentModelList.add(new CommentModel(1, 1,"First comment"));
        commentModelList.add(new CommentModel(2, 1,"Second comment"));
    }

    @Override
    public boolean createComment(CommentModel commentModel) {
        commentModel.setCommentId(commentIdCounter);
        commentModelList.add(commentModel);
        commentIdCounter++;
        return true;
    }

    @Override
    public CommentModel getCommentWithID(int id) {
        for(CommentModel c: commentModelList){
            if(c.getCommentId() == id){
                return c;
            }
        }
        return null;
    }

    @Override
    public CommentModel getCommentWithVersionID(int id) {
        for(CommentModel c: commentModelList){
            if(c.getVersionId() == id){
                return c;
            }
        }
        return null;
    }

    @Override
    public List<CommentModel> getComments() {
        List<CommentModel> comments = new ArrayList<>();
        for(CommentModel c: commentModelList){
            comments.add(c);
        }
        return comments;
    }
}
