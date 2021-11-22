package com.g3.feedbackApp.Repository;

import com.g3.feedbackApp.Models.PostModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPostRepository extends JpaRepository<PostModel, Long> {

    PostModel getFirstByPostId(Long id);
}
