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

    private AdvertisementDto advertisementDto;

    @NotNull(message = "Approved cannot be null")
    private boolean approved = false;

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public AdvertisementDto getAdvertisementDto() {
        return advertisementDto;
    }

    public void setAdvertisementDto(AdvertisementDto advertisementDto) {
        this.advertisementDto = advertisementDto;
    }

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

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }
}
