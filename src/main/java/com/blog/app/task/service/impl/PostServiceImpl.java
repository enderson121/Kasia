package com.blog.app.task.service.impl;

import com.blog.app.task.dto.PostUserDTO;
import com.blog.app.task.exception.MessageException;
import com.blog.app.task.model.Post;
import com.blog.app.task.model.User;
import com.blog.app.task.repository.PostRepository;
import com.blog.app.task.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;


    @Override
    public void createPost(Post post) throws MessageException {
        Post save = postRepository.save(post);
        if (save.getId() == null) throw new MessageException("Error creating post by user");
    }

    @Override
    public PostUserDTO visit(Integer idPost) throws MessageException {
        Post post = findPostById(idPost);
        return updateVisitsByPost(post);
    }

    @Override
    public Collection<PostUserDTO> getAllByUser(int page, int size,int idUser) throws MessageException{
        Pageable paging = PageRequest.of(page, size);
        Page<Post> allPostByUserPaging = postRepository.findAllByUserId(idUser,paging);
        List<Post> content = allPostByUserPaging.getContent();
        List<PostUserDTO> postUserDTOS = mappearPostUser(content);
        if(postUserDTOS.isEmpty())throw new MessageException("User does not register posts");
        return postUserDTOS;

    }

    @Override
    public void addComment(User author, Post post, String content) {

    }
    private Post findPostById(Integer id) throws MessageException{
        Optional<Post> post = postRepository.findById(id);
        if(!post.isPresent()) throw new MessageException("Post not found");
        return post.get();
    }
    private PostUserDTO updateVisitsByPost(Post post) throws MessageException{
        Integer numberViews = post.getViews();
        post.setViews(findConsecutive(numberViews));
        post.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        Post save = postRepository.save(post);
        if(save.getId() == null) throw new MessageException("Error loading post");
        return mappearPostUser(save);
    }
    private Integer findConsecutive(Integer numberViews){
        return numberViews > 0
                ? numberViews + 1
                : 0;
    }

    private List<PostUserDTO> mappearPostUser(List<Post> posts){
        List<PostUserDTO> postUserDTOS = new ArrayList<>();
        posts.forEach(
                post ->postUserDTOS.add(mappearPostUser(post)));
        return postUserDTOS;
    }
    private PostUserDTO mappearPostUser(Post post){
        return new PostUserDTO(
                post.getId(),
                post.getViews(),
                post.getTitle(),
                post.getBody(),
                post.getUser().getFirstName()+" " +post.getUser().getLastName(),
                post.getUser().getEmail());
    }
}
