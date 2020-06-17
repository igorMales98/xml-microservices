package com.xml.controller;

import com.xml.dto.CommentDto;
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

    @GetMapping(value = "/{adId}")
    public ResponseEntity<?> getAll(@PathVariable("adId") Long adId, @RequestHeader("Authorization") String token) {
        System.out.println(token);
        try {
            List<CommentDto> commentDtos = this.commentService.getAll(adId, token);
            return new ResponseEntity<>(commentDtos, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/reply")
    public ResponseEntity<?> sendReply(@Valid @RequestBody CommentDto commentDto) {
        try {

            this.commentService.sendReply(commentDto.getId(), commentDto.getReply());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "")
    public ResponseEntity<?> postComment(@Valid @RequestBody CommentDto commentDto) {
        try {
            this.commentService.postComment(commentDto);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
