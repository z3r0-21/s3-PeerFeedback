package com.g3.feedbackApp.Models.DTOS;

import com.g3.feedbackApp.Models.VersionModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
public class PostDTO {

    private int postId;
    private int idOP;
    private String title;
    private String category;
    private String description;
    private LocalDate postDate;
    private LocalDate resolveDate;
    private Path filePath;
    private MultipartFile uploadedFile;
    private List<VersionModel> versions;
    private List<Integer> reviewersIds;

    public PostDTO(int postID, int idOP ,String title, String category, String description, LocalDate postDate, LocalDate resolveDate){
        this.postId = postID;
        this.idOP = idOP;
        this.title = title;
        this.category = category;
        this.description = description;
        this.postDate = postDate;
        this.resolveDate = resolveDate;
    }
}
