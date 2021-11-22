package com.g3.feedbackApp.Models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name ="reviewer")
public class ReviewerModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "postId")
    private Long postId;
    @Column(name = "userId")
    private Long userId;

    public ReviewerModel(Long postId, Long userId){
        this.postId = postId;
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReviewerModel that = (ReviewerModel) o;
        return id == that.id && postId == that.postId && userId == that.userId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, postId, userId);
    }
}
