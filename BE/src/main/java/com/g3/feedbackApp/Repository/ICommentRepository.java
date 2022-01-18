package com.g3.feedbackApp.Repository;

import com.g3.feedbackApp.Models.CommentModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICommentRepository extends JpaRepository<CommentModel, Long> {

    CommentModel getFirstByCommentId(Long id);
    List<CommentModel> getCommentModelsByVersionId(Long id);
    void deleteAllByVersionId(Long versionId);

}
