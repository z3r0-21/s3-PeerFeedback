package com.g3.feedbackApp.Models;

import lombok.Getter;
import lombok.Setter;

import java.nio.file.Path;

@Getter
@Setter
public class VersionModel {

    private Long versionId;
    private Long postId;
    private Path filePath;

    public VersionModel(Long versionId, Long postId, Path filePath){
        this.versionId = versionId;
        this.postId = postId;
        this.filePath = filePath;
    }
}
