package com.blog.app.task.service;


import com.blog.app.task.dto.PostUserDTO;
import com.blog.app.task.exception.MessageException;
import com.blog.app.task.model.Post;
import com.blog.app.task.model.User;
import org.springframework.stereotype.Service;

import java.util.Collection;

/***
 *
 *  TODO:
 *
 * **/
@Service
public interface PostService {

    /**
     *
     * Create a post with a given user id in the request
     *
     * **/
    void createPost(Post post) throws MessageException;

    /***
     *
     * Get a post's information and register a visit to it
     *
     * **/
    PostUserDTO visit(Integer idPost) throws MessageException;

    /**
     * Get all posts made by a given user (paginated)
     * **/
    Collection<PostUserDTO> getAllByUser(int page, int size,int idUser) throws MessageException;

    /**
     * Add a comment to a post
     */
    void addComment(User author, Post post, String content);

}
