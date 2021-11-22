package com.g3.feedbackApp.Models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="post")
public class PostModel {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long postId;
    @Column(name="idOP")
    private int idOP;
    @Column(name="title")
    private String title;
    @Column(name="category")
    private String category;
    @Column(name="description")
    private String description;
    @Column(name="postDate")
    private LocalDate postDate;
    @Column(name="resolveDate")
    private LocalDate resolveDate;

    public PostModel(int idOP, String title, String category, String description, LocalDate postDate, LocalDate resolveDate){
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
        return Objects.hash();
    }
}
