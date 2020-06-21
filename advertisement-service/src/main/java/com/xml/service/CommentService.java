package com.xml.service;

import com.xml.dto.CommentDto;

import java.util.List;

public interface CommentService {
    List<CommentDto> getAll(String token);
    List<CommentDto> getApproved(Long adId, String token);

    void sendReply(Long id, String reply);

    void postComment(CommentDto commentDto);

    void approveComment(CommentDto commentDto);

    void deleteComment(CommentDto commentDto);
}
