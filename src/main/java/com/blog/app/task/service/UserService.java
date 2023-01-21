package com.blog.app.task.service;

import com.blog.app.task.dto.UserDTO;
import com.blog.app.task.exception.MessageException;
import com.blog.app.task.model.User;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface UserService {

    /**
     *
     * Register a user
     *
     * **/
    void register(User user) throws MessageException;

    /**
     *
     * Get all users (paginated)
     *
     * **/
    Collection<UserDTO> findAllUsers(int page, int size) throws MessageException;

    /**
     * Find a user by email
     */
    UserDTO findUserByMail(String email) throws MessageException;

}
