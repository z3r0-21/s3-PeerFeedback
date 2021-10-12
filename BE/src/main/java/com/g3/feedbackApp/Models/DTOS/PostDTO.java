package com.g3.feedbackApp.Models.DTOS;

import java.time.LocalDate;

public class PostDTO {

    private int postId;
    private int idOP;
    private String title;
    private String category;
    private String description;
    private LocalDate postDate;
    private LocalDate resolveDate;
    private String filePath;

    public PostDTO(int postID, int idOP ,String title, String category, String description, LocalDate postDate, LocalDate resolveDate, String filePath){
        this.postId = postID;
        this.idOP = idOP;
        this.title = title;
        this.category = category;
        this.description = description;
        this.postDate = postDate;
        this.resolveDate = resolveDate;
        this.filePath = filePath;
    }

    public int getPostId(){return this.postId;}
    public void setPostId(int postId){this.postId = postId;}
    public int getIdOP(){return this.idOP;}
    public void setIdOP(int idOP){this.idOP = idOP;}
    public String getTitle(){return this.title;}
    public void setTitle(String title){this.title = title;}
    public String getDescription(){return this.description;}
    public void setDescription(String description){this.description = description;}
    public String getCategory(){return this.category;}
    public void setCategory(String category){this.category = category;}
    public LocalDate getPostDate(){return this.postDate;}
    public void setLocalDate(LocalDate postDate){this.postDate = postDate;}
    public LocalDate getResolveDate(){return this.resolveDate;}
    public void setResolveDate(LocalDate resolveDate){this.resolveDate = resolveDate;}
    public String getFilePath(){return this.filePath;}
    public void setFilePath(String filePath){this.filePath = filePath;}
}
