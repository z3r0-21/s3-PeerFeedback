package com.g3.feedbackApp.Models.DTOS;

import com.g3.feedbackApp.Models.VersionModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import java.util.LongSummaryStatistics;

@Setter
@Getter
@NoArgsConstructor
public class VersionDTO {

    private Long versionId;
    private Long versionCounter;
    private Long postId;
    private String filePath;

    public VersionDTO(Long versionId, Long versionCounter, Long postId, String filePath){
        setVersionId(versionId);
        setVersionCounter(versionCounter);
        setPostId(postId);
        setFilePath(filePath);
    }
}