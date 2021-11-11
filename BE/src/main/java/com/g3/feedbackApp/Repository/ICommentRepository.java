package com.g3.feedbackApp.Repository;

import com.g3.feedbackApp.Models.CommentModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICommentRepository extends JpaRepository<CommentModel, Integer> {

    CommentModel getFirstByCommentId(int id);
    List<CommentModel> getCommentModelsByVersionId(int id);
}
