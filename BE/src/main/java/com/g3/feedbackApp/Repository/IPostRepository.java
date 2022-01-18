package com.g3.feedbackApp.Repository;

import com.g3.feedbackApp.Models.PostModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPostRepository extends JpaRepository<PostModel, Long> {

    PostModel getFirstByPostId(Long id);
    void deleteAllByIdOP(int idOp);
    List<PostModel> getAllByIdOP(int idOp);
}
