package com.xml.repository;

import com.xml.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    ArrayList<Comment> getAllByAdvertisement_id(Long adId);

    Comment findOneById(Long id);

    List<Comment> findAllByCommenterId(Long commenterId);
}
