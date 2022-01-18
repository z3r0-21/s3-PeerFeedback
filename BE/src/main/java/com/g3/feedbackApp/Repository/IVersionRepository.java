package com.g3.feedbackApp.Repository;

import com.g3.feedbackApp.Models.VersionModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IVersionRepository extends JpaRepository<VersionModel, Long> {

    VersionModel getFirstByVersionId(Long id);

    List<VersionModel> getVersionModelsByPostId(Long id);

    void deleteAllByPostId(Long postId);
}
