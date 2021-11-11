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
    private Integer commentId;
    @Column(name = "userId")
    private Integer userId;
    @Column(name = "versionId")
    private Integer versionId;
    @Column(name = "text")
    private String text;
    @Column(name = "isSolution")
    private boolean isSolution;

    public CommentModel(Integer userId, Integer versionId, String text){
        this.userId = userId;
        this.versionId = versionId;
        this.text = text;
        this.isSolution = false;
    }
}