package com.g3.feedbackApp.Models;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
public class PostModel {

    private Long postId;
    private int idOP;
    private String title;
    private String category;
    private String description;
    private LocalDate postDate;
    private LocalDate resolveDate;

    public PostModel(Long postID, int idOP, String title, String category, String description, LocalDate postDate, LocalDate resolveDate){
            this.postId = postID;
            this.idOP = idOP;
            this.title = title;
            this.category = category;
            this.description = description;
            this.postDate = postDate;
            this.resolveDate = resolveDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostModel postModel = (PostModel) o;
        return Objects.equals(hashCode(), postModel.hashCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(postId);
    }
}
