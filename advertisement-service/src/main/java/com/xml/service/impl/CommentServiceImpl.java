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
        List<Comment> allComments = commentRepository.getAllByAdvertisement_id(adId);
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
            UserDto advertiserDto = userFeignClient.getUserById(comment.getCommenterId(), token);
            commentDto.setCommenter(advertiserDto);
            allApprovedComments.add(commentDto);
        }
        return allApprovedComments;
    }

    @Override
    public void sendReply(Long id, String reply) {
        System.out.println(reply);
        Comment comment = commentRepository.findOneById(id);
        comment.setReply(reply);
        commentRepository.save(comment);
    }

}
