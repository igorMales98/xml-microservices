package com.xml.controller;

import com.xml.dto.CommentDto;
import com.xml.model.Comment;
import com.xml.service.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin(value = "https://localhost:4200")
@RequestMapping(value = "/api/comments", produces = MediaType.APPLICATION_JSON_VALUE)
public class CommentController {

    @Autowired
    private CommentService commentService;

    Logger logger = LoggerFactory.getLogger(CommentController.class);

    @GetMapping(value = "")
    @PreAuthorize("hasAuthority('READ_COMMENTS')")
    public ResponseEntity<?> getAll(@RequestHeader("Authorization") String token) {
        try {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            logger.info("Date: {}, A user with username: {} try to get all comments.", LocalDateTime.now(), userDetails.getUsername());
            List<CommentDto> commentDtos = this.commentService.getAll(token);
            logger.info("Date : {}, Successfully returned list of all comments.", LocalDateTime.now());
            return new ResponseEntity<>(commentDtos, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Date : {}, There was an error to get all comments. " +
                    "Error : {}.", LocalDateTime.now(), e.toString());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/{adId}")
    @PreAuthorize("hasAuthority('READ_APPROVED_COMMENTS')")
    public ResponseEntity<?> getApproved(@PathVariable("adId") Long adId, @RequestHeader("Authorization") String token) {
        try {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            logger.info("Date: {}, A user with username: {} try to get all approved comment.", LocalDateTime.now(), userDetails.getUsername());
            List<CommentDto> commentDtos = this.commentService.getApproved(adId, token);
            logger.info("Date : {}, Successfully returned list of all approved comments.", LocalDateTime.now());
            return new ResponseEntity<>(commentDtos, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Date : {}, There was an error to get all approved comments. " +
                    "Error : {}.", LocalDateTime.now(), e.toString());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "")
    @PreAuthorize("hasAuthority('REPLY_COMMENT')")
    public ResponseEntity<?> sendReply(@Valid @RequestBody CommentDto commentDto) {
        try {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            logger.info("Date: {}, A user with username: {} try to sent reply for comment.", LocalDateTime.now(), userDetails.getUsername());
            this.commentService.sendReply(commentDto.getId(), commentDto.getReply());
            logger.info("Date : {}, A user with username: {} has successfully sent reply for comment.", LocalDateTime.now(), userDetails.getUsername());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Date : {}, There was an error to sending reply for comment. " +
                    "Error : {}.", LocalDateTime.now(), e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "")
    @PreAuthorize("hasAuthority('CREATE_COMMENT')")
    public ResponseEntity<?> postComment(@RequestBody CommentDto commentDto) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        try {
            logger.info("Date: {}, A user with username: {} try to posted comment.", LocalDateTime.now(), userDetails.getUsername());
            System.out.println("ad: " + commentDto.getAdvertisementDto().getId());
            this.commentService.postComment(commentDto);
        } catch (Exception e) {
            System.out.println(e);
            logger.error("Date : {}, There was an error to posting comment. " +
                    "Error : {}.", LocalDateTime.now(), e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        logger.info("Date : {}, A user with username: {} has successfully posted comment.", LocalDateTime.now(), userDetails.getUsername());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/approve/{id}")
    @PreAuthorize("hasAuthority('APPROVE_COMMENT')")
    public ResponseEntity<?> approveComment(@PathVariable Long id) {
        try {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            logger.info("Date: {}, A user with username: {} try to get approved comment.", LocalDateTime.now(), userDetails.getUsername());
            this.commentService.approveComment(id);
            logger.info("Date : {}, A user with username: {} has successfully approved comment.", LocalDateTime.now(), userDetails.getUsername());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            logger.info("Date : {}, There was an error to approving comment. " +
                    "Error : {}.", LocalDateTime.now(), e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('DECLINE_COMMENT')")
    public ResponseEntity<?> deleteComment(@PathVariable Long id) {
        try {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            logger.info("Date: {}, A user with username: {} try to deleted comment.", LocalDateTime.now(), userDetails.getUsername());
            this.commentService.deleteComment(id);
            logger.info("Date : {}, A user with username: {} has successfully deleted comment.", LocalDateTime.now(), userDetails.getUsername());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            logger.info("Date : {}, There was an error to deleting comment. " +
                    "Error : {}.", LocalDateTime.now(), e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/{commenterId}/{adId}")
    @PreAuthorize("hasAuthority('FEEDBACK')")
    public ResponseEntity<?> sentFeedback(@PathVariable("commenterId") Long commenterDto, @PathVariable("adId") Long adId) {
        try {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            logger.info("Date: {}, A user with username: {} try to sent feedback for comment.", LocalDateTime.now(), userDetails.getUsername());
            boolean retVal = this.commentService.sentFeedback(commenterDto, adId);
            logger.info("Date : {}, A user with username: {} has successfully sent feedback for comment.", LocalDateTime.now(), userDetails.getUsername());
            return new ResponseEntity<>(retVal, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            System.out.println(e);
            logger.error("Date : {}, There was an error to sending feedback for comment. " +
                    "Error : {}.", LocalDateTime.now(), e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
