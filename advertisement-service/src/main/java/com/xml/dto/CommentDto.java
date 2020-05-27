package com.xml.dto;


public class CommentDto {

    private Long id;

    private Long commenterId;

    private String comment;

    private String reply;


    public CommentDto() {
    }

    public Long getId() {
        return id;
    }

    public Long getCommenterId() {
        return commenterId;
    }

    public String getComment() {
        return comment;
    }

    public String getReply() {
        return reply;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCommenterId(Long commenterId) {
        this.commenterId = commenterId;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }


}
