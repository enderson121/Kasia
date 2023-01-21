package com.blog.app.task.controller;

import com.blog.app.task.dto.CommentPostUserDTO;
import com.blog.app.task.exception.MessageException;
import com.blog.app.task.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(path = "${communication.context.path.comment}")
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
     * Add a comment to a post
     */
    @PostMapping(path = "/createComment",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createComment(RequestEntity<CommentPostUserDTO> requestEntity){
        try{
            CommentPostUserDTO comment = requestEntity.getBody();
            commentService.addComment(comment);
            return new ResponseEntity<>("Post Created by User", HttpStatus.OK);
        }catch(MessageException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(),e);
        }
    }
}
