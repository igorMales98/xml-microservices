package com.xml.service.impl;

import com.xml.dto.CommentDto;
import com.xml.dto.UserDto;
import com.xml.feignClients.UserFeignClient;
import com.xml.model.Advertisement;
import com.xml.model.Comment;
import com.xml.repository.AdvertisementRepository;
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
    private AdvertisementRepository advertisementRepository;

    @Autowired
    private UserFeignClient userFeignClient;

    @Override
    public List<CommentDto> getApproved(Long adId, String token) {
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
            commentDto.setApproved(comment.isApproved());
            UserDto advertiserDto = this.userFeignClient.getUserById(comment.getCommenterId(), token);
            commentDto.setCommenter(advertiserDto);
            allApprovedComments.add(commentDto);
        }
        return allApprovedComments;
    }

    @Override
    public List<CommentDto> getAll(String token) {
        List<Comment> comments = this.commentRepository.findAll();
        List<CommentDto> commentDtos = new ArrayList<>();

        for (Comment comment : comments) {
            CommentDto commentDto = new CommentDto();
            commentDto.setComment(comment.getComment());
            commentDto.setReply(comment.getReply());
            commentDto.setId(comment.getId());
            commentDto.setApproved(comment.isApproved());
            commentDto.setRejected(comment.isRejected());
            UserDto advertiserDto = this.userFeignClient.getUserById(comment.getCommenterId(), token);
            commentDto.setCommenter(advertiserDto);
            commentDtos.add(commentDto);
        }
        return commentDtos;
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
        comment.setComment(commentDto.getComment());
        System.out.println("komentar1: "+commentDto.getAdvertisementDto().getId()+commentDto.getAdvertisementDto().getAvailableFrom());
        Advertisement advertisement = this.advertisementRepository.getOne(commentDto.getAdvertisementDto().getId());
        System.out.println("komentar2: "+commentDto.getComment());
        comment.setAdvertisement(advertisement);
        comment.setCommenterId(commentDto.getCommenter().getId());
        System.out.println("komentar3: "+commentDto.getComment());
        this.commentRepository.save(comment);
        System.out.println("komentar: "+comment.getComment());
    }

    @Override
    public void approveComment(Long id) {
        Comment comment = this.commentRepository.findById(id).get();
        comment.setApproved(true);
        this.commentRepository.save(comment);
    }

    @Override
    public void deleteComment(Long id) {
        Comment comment = this.commentRepository.findById(id).get();
        comment.setRejected(true);
        this.commentRepository.save(comment);
    }

}
