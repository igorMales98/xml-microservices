package com.xml.controller;

import com.xml.dto.CommentDto;
import com.xml.model.Comment;
import com.xml.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(value = "https://localhost:4200")
@RequestMapping(value = "/api/comments", produces = MediaType.APPLICATION_JSON_VALUE)
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping(value = "")
    public ResponseEntity<?> getAll(@RequestHeader("Authorization") String token) {
        try {
            List<CommentDto> commentDtos = this.commentService.getAll(token);
            return new ResponseEntity<>(commentDtos, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/{adId}")
    public ResponseEntity<?> getApproved(@PathVariable("adId") Long adId, @RequestHeader("Authorization") String token) {
        try {
            List<CommentDto> commentDtos = this.commentService.getApproved(adId, token);
            return new ResponseEntity<>(commentDtos, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "")
    public ResponseEntity<?> sendReply(@Valid @RequestBody CommentDto commentDto) {
        try {
            this.commentService.sendReply(commentDto.getId(), commentDto.getReply());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "")
    public ResponseEntity<?> postComment(@RequestBody CommentDto commentDto) {
        try {
            System.out.println("ad: "+commentDto.getAdvertisementDto().getId());
            this.commentService.postComment(commentDto);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/approve/{id}")
    public ResponseEntity<?> approveComment(@PathVariable Long id) {
        try {
            this.commentService.approveComment(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable Long id) {
        try {
            this.commentService.deleteComment(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
