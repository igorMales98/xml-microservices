package com.xml.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


public class CommentDto {

    private Long id;

    @NotNull(message = "Commenter cannot be null")
    private UserDto commenter;

    @NotBlank(message = "Comment cannot be empty")
    private String comment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserDto getCommenter() {
        return commenter;
    }

    public void setCommenter(UserDto commenter) {
        this.commenter = commenter;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
