package com.blog.app.task.service.impl;

import com.blog.app.task.dto.CommentPostUserDTO;
import com.blog.app.task.exception.MessageException;
import com.blog.app.task.model.Comment;
import com.blog.app.task.model.Post;
import com.blog.app.task.model.User;
import com.blog.app.task.repository.CommentRepository;
import com.blog.app.task.repository.PostRepository;
import com.blog.app.task.repository.UserRepository;
import com.blog.app.task.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentRepository commentRepository;
    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public void addComment(CommentPostUserDTO commentPostUserDTO) throws MessageException {
        Comment comment = new Comment();
        Optional<User> users = userRepository.findById(commentPostUserDTO.getIdUser());
        Optional<Post> post = postRepository.findById(commentPostUserDTO.getIdPost());
        comment.setPost(post.get());
        comment.setAuthor(users.get());
        comment.setBody(commentPostUserDTO.getBody());
        comment.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        comment.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        Comment save = commentRepository.save(comment);
        if (save.getId() == null) throw new MessageException("Error add a comment to a post");
    }
}
