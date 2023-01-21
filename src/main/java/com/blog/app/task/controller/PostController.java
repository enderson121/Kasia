package com.blog.app.task.controller;

import com.blog.app.task.dto.PostUserDTO;
import com.blog.app.task.exception.MessageException;
import com.blog.app.task.model.Post;
import com.blog.app.task.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;

@RestController
@RequestMapping(path = "${communication.context.path.post}")
public class PostController {

	@Autowired
	private PostService postService;

    /**
     * Create a post with a given user id in the request
     * **/
	@PostMapping(path = "/create",
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createPostByUser(RequestEntity<Post> requestEntity){
		try{
			Post post = requestEntity.getBody();
			postService.createPost(post);
			return new ResponseEntity<>("Post Created by User", HttpStatus.OK);
		}catch(MessageException e){
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(),e);
		}
	}


    /***
     *
     * Get a post's information and register a visit to it
     *
     * **/
	@GetMapping(path = "/view/{idpost}",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PostUserDTO> viewPost(
			@PathVariable(value = "idpost") Integer idpost)
	{
		try {
			return ResponseEntity.status(HttpStatus.OK).body(postService.visit(idpost));
		}catch (MessageException e){
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage(),e);
		}
	}


    /**
     * Get all posts made by a given user (paginated)
     * **/

	@GetMapping(path = "/posts/{page}/{size}/{user}",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<PostUserDTO>> getAllPostMadeUser(
			@PathVariable(value = "page") int page,
			@PathVariable(value = "size") int size,
			@PathVariable(value = "user") int idUser)
	{
		try{
			Collection<PostUserDTO> postUserDTOS = postService.getAllByUser(page, size,idUser);
			return ResponseEntity.status(HttpStatus.OK).body(postUserDTOS);
		}catch (MessageException e){
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage(),e);
		}

	}

	/**
	 * Add a comment to a post
	 */
	@PostMapping(path = "/createComment",
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createComment(RequestEntity<Post> requestEntity){
		try{
			Post post = requestEntity.getBody();
			postService.createPost(post);
			return new ResponseEntity<>("Post Created by User", HttpStatus.OK);
		}catch(MessageException e){
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(),e);
		}
	}
}
