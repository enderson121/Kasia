package com.blog.app.task.service;

import com.blog.app.task.dto.CommentPostUserDTO;
import com.blog.app.task.exception.MessageException;
import org.springframework.stereotype.Service;

@Service
public interface CommentService {

    void addComment(CommentPostUserDTO comment) throws MessageException;
}
