package com.g3.feedbackApp.Models.DTOS;

public class CommentDTO {

    private Integer commentId;
    private Integer versionId;
    private String text;
    private boolean isSolution;

    public CommentDTO(Integer commentId, Integer versionId, String text){
        this.commentId = commentId;
        this.versionId = versionId;
        this.text = text;
        this.isSolution = false;
    }

    public Integer getCommentId(){ return commentId; }
    public void setCommentId(Integer versionId) { this.commentId = commentId; }
    public Integer getVersionId(){ return versionId; }
    public void setVersionId(Integer versionId) { this.versionId = versionId; }
    public String getText(){ return text; }
    public void setText(String text) { this.text = text; }
    public boolean getSolution() { return isSolution; }
    public void setSolution() { isSolution = true; }
}
