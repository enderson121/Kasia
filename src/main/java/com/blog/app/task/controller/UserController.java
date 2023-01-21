package com.blog.app.task.controller;

import com.blog.app.task.dto.UserDTO;
import com.blog.app.task.exception.MessageException;
import com.blog.app.task.model.User;
import com.blog.app.task.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;

@RestController
@RequestMapping(path = "${communication.context.path.user}")
public class UserController {
	@Autowired
	private UserService userService;

	@PostMapping(path = "/create",
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> postRegisterUser(RequestEntity<User> requestEntity) {
		try {
			User user = requestEntity.getBody();
			userService.register(user);
			return new ResponseEntity<>("Created User", HttpStatus.OK);

		}catch (MessageException e){
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage(),e);
		}
	}

    /**
     * Get all users (paginated)
     * **/
	@GetMapping(path = "/users/{page}/{size}",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<UserDTO>> getAllUsers(
			@PathVariable(value = "page") int page,
			@PathVariable(value = "size") int size)
	{
		try{
			Collection<UserDTO> allUsers = userService.findAllUsers(page, size);
			return ResponseEntity.status(HttpStatus.OK).body(allUsers);
		}catch (MessageException e){
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage(),e);
		}

	}

	/**
	 * Find a user by email
	 */
	@GetMapping(path = "/email/{email}",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDTO> getUserByEmail(@PathVariable(value = "email") String email){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(userService.findUserByMail(email));
		}catch (MessageException e){
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage(),e);
		}
	}
}
