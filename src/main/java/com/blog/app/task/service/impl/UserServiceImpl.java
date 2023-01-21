package com.blog.app.task.service.impl;

import com.blog.app.task.dto.UserDTO;
import com.blog.app.task.exception.MessageException;
import com.blog.app.task.service.UserService;
import com.blog.app.task.model.User;
import com.blog.app.task.repository.UserRepository;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveUser;
    @Override
    public void register(User user) throws MessageException {
        String email = user.getEmail();
        validatorExistsEmail(email);
        saveUser = userRepository.save(user);
        if(saveUser == null) throw new MessageException("Error creating user");
    }

    @Override
    public Collection<UserDTO> findAllUsers(int page, int size) throws MessageException{
        Pageable paging = PageRequest.of(page, size);
        Page<User> allUserPaging = userRepository.findAll(paging);
        if(allUserPaging.hasContent()){
            List<User> content = allUserPaging.getContent();
            List<UserDTO> userDTOS = mappearUser(content);
            if(userDTOS.isEmpty()) throw new MessageException("No registered users");
            return userDTOS;
        }else{
            throw new MessageException("no found information");
        }


    }

    @Override
    public UserDTO findUserByMail(String email) throws MessageException {
        emailValidations(email);
        User user = findUserByEmailRepository(email);
        if(user == null) throw new MessageException("user not found");
        return mappearUser(user);
    }

    public User findUserByEmailRepository(String email) {
        return userRepository.findTop1ByEmail(email);
    }

    private void emailValidations(String email) throws MessageException{
        if (email.isEmpty()) throw new MessageException("insufficient email address");
        if(!EmailValidator.getInstance().isValid(email)) throw new MessageException("Email address not accepted");

    }

    private void validatorExistsEmail(String email) throws MessageException{
        emailValidations(email);
        User userEmail = findUserByEmailRepository(email);
        if(userEmail != null){
            throw new MessageException("User exists");
        }
    }

    private List<UserDTO> mappearUser(List<User> users){
        List<UserDTO> userDTOS = new ArrayList<>();
        users.forEach(
                user ->userDTOS.add(mappearUser(user)));
        return userDTOS;
    }
    private UserDTO mappearUser(User user){
        return new UserDTO(
                user.getFirstName(),
                user.getLastName(),
                user.getEmail());
    }
}
