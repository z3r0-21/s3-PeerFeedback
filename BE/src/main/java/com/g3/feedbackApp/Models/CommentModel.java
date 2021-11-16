package com.g3.feedbackApp.Models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name ="comment")
public class CommentModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long commentId;
    @Column(name = "userId")
    private Long userId;
    @Column(name = "versionId")
    private Long versionId;
    @Column(name = "text")
    private String text;
    @Column(name = "isSolution")
    private boolean isSolution;

    public CommentModel(Long userId, Long versionId, String text){
        this.userId = userId;
        this.versionId = versionId;
        this.text = text;
        this.isSolution = false;
    }
}