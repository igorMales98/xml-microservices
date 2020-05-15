package com.xml.model;

import com.xml.dto.CommentDto;

import javax.persistence.*;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "commenter_id", nullable = false)
    private User commenter;

    @Column
    private String comment;

    @Column
    private boolean approved = false;

    @Column
    private String reply;

    //@ManyToOne
    //@JoinColumn(name = "advertisement_id")
    //private Advertisement advertisement;

    public Comment() {

    }
    //public Comment(CommentDto commentDto) {
    //    this.id = commentDto.getId();
    //    this.comment = commentDto.getComment();
    //    this.commenter = commentDto.getCommenter();
    //    this.reply = commentDto.getReply();
    //}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getCommenter() {
        return commenter;
    }

    public void setCommenter(User commenter) {
        this.commenter = commenter;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    //public Advertisement getAdvertisement() {
    //    return advertisement;
    //}

   // public void setAdvertisement(Advertisement advertisement) {
   //     this.advertisement = advertisement;
   // }

}
