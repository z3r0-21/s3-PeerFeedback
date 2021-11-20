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
public class PostDTO {

    private Long postId;
    private int idOP;
    private String title;
    private String category;
    private String description;
    private LocalDate postDate;
    private LocalDate resolveDate;
    private Path filePath;
    private MultipartFile uploadedFile;
    private List<VersionModel> versions;
    private List<Long> reviewersIds;

    public PostDTO(Long postID, int idOP ,String title, String category, String description, LocalDate postDate, LocalDate resolveDate){
        setPostId(postID);
        setIdOP(idOP);
        setTitle(title);
        setCategory(category);
        setDescription(description);
        setPostDate(postDate);
        setResolveDate(resolveDate);
    }
}
