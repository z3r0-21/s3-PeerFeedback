package com.g3.feedbackApp.Models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.nio.file.Path;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name ="version")
public class VersionModel {

    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long versionId = 0l;
    @Column(name = "postId")
    private Long postId;
    @Column(name = "filePath")
    private String filePath;

    public VersionModel(Long versionId, Long postId, String filePath){
        this.versionId = versionId;
        this.postId = postId;
        this.filePath = filePath;
    }
}
