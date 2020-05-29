package com.xml.controller;

import com.xml.dto.CommentDto;
import com.xml.mapper.CommentDtoMapper;
import com.xml.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(value = "https://localhost:4200")
@RequestMapping(value = "/api/comment", produces = MediaType.APPLICATION_JSON_VALUE)
public class CommentController {

    @Autowired
    private CommentService commentService;
    @Autowired
    private CommentDtoMapper commentDtoMapper;

    @GetMapping(value = "/all/{adId}")
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
}
