package com.xml.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


public class CommentDto {

    private Long id;

    @NotNull(message = "Commenter cannot be null")
    private UserDto commenter;

    @NotBlank(message = "Comment cannot be empty")
    private String comment;

    private String reply;

    @NotNull(message = "Approved cannot be null")
    private boolean approved = false;

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public CommentDto() {
    }

    public Long getId() {
        return id;
    }

    public UserDto getCommenter() {
        return commenter;
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

    public void setCommenter(UserDto commenter) {
        this.commenter = commenter;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }


}
