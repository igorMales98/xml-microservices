package com.xml.service.impl;

import com.xml.dto.CommentDto;
import com.xml.dto.UserDto;
import com.xml.feignClients.UserFeignClient;
import com.xml.model.Comment;
import com.xml.repository.CommentRepository;
import com.xml.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserFeignClient userFeignClient;

    @Override
    public List<CommentDto> getAll(Long adId, String token) {
        List<Comment> allComments = this.commentRepository.getAllByAdvertisement_id(adId);
        List<Comment> approvedComments = new ArrayList<>();
        for (Comment comment : allComments) {
            if (comment.isApproved()) {
                approvedComments.add(comment);
            }
        }

        List<CommentDto> allApprovedComments = new ArrayList<>();

        for (Comment comment : approvedComments) {
            CommentDto commentDto = new CommentDto();
            commentDto.setComment(comment.getComment());
            commentDto.setReply(comment.getReply());
            commentDto.setId(comment.getId());
            UserDto advertiserDto = this.userFeignClient.getUserById(comment.getCommenterId(), token);
            commentDto.setCommenter(advertiserDto);
            allApprovedComments.add(commentDto);
        }
        return allApprovedComments;
    }

    @Override
    public void sendReply(Long id, String reply) {
        System.out.println(reply);
        Comment comment = this.commentRepository.findOneById(id);
        comment.setReply(reply);
        this.commentRepository.save(comment);
    }

    @Override
    public void postComment(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setCommenterId(commentDto.getCommenter().getId());
        comment.setComment(commentDto.getComment());
        //    Advertisement ad = this.advertisementRepository.getOne(commentDto.getAdvertisement().getId());
        //    Set<Comment> comments = ad.getComments();
        //    comments.add(comment);
        //    ad.setComments(comments);
        this.commentRepository.save(comment);
        //    this.advertisementRepository.save(ad);
        //treba da se posalje i za koji oglas je vezan komentar
        //moze u commentDto ili u zahtevu da se doda, restful
    }

}
