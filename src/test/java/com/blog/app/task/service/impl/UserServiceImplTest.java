package com.blog.app.task.service.impl;

import com.blog.app.task.dto.UserDTO;
import com.blog.app.task.exception.MessageException;
import com.blog.app.task.model.User;
import com.blog.app.task.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatObject;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserServiceImpl userService;

    private User user;

    @Test
    void registerErrorCreatingUserTest() throws Exception {
        user = createUser("colombo121111@gmail.com");
        try {
            userService.register(user);
            verify(userRepository,times(1)).save(any());
        }catch (MessageException e){
            assertEquals("Error creating user",e.getMessage());
        }
    }

    @Test
    void registerUserTest() throws Exception {
        user = createUser("colombo121111@gmail.com");
        when(userRepository.save(any(User.class))).thenReturn(user);

        userService.register(user);
        assertThatObject(userRepository).isNotNull();
        assertEquals(user.getEmail(),userService.saveUser.getEmail());

    }

    @Test
    void registerExistingTest() throws Exception {
        user = createUser("colombo121111@gmail.com");
        try {
            when(userRepository.findTop1ByEmail(user.getEmail())).thenReturn(user);
            //when(userRepository.save(any(User.class))).thenReturn(user);
            userService.register(user);
            assertThatObject(userRepository).isNotNull();

        }catch (MessageException e){
            assertEquals("User exists",e.getMessage());
        }

    }

//    @Test
//    void findAllUsersSuccessfulTest(){
//        user = createUser("colombo121111@gmail.com");
//        Pageable paging = PageRequest.of(50, 10);
//        try {
//            Sort sortMock = Mockito.mock(Sort.class);
//            when(paging.getSort()).thenReturn(sortMock);
//            when(sortMock.isSorted()).thenReturn(true);
//            when(userRepository.findAll(paging)).thenReturn((Page<User>) user);
//            Collection<UserDTO> allUsers = userService.findAllUsers(50, 10);
////            when(requestPage.getSort())
////            when(userRepository.findAll(paging)).thenReturn((Page<User>) user);
//            //when(userRepository.save(any(User.class))).thenReturn(user);
//
//            assertThatObject(allUsers).isNotNull();
//        }catch (MessageException e){
//            assertEquals("User exists",e.getMessage());
//        }
//    }
//
    private User createUser(String email){
        Timestamp date = new Timestamp(new Date(2023,01,21).getTime());
        return new User(
                0,
                "",
                "",
                email,
                "",
                date,
                date);
    }
}