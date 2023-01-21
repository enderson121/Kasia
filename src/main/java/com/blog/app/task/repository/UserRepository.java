package com.blog.app.task.repository;

import com.blog.app.task.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>, PagingAndSortingRepository<User,Integer> {

    //QueryMethod
    User findTop1ByEmail(String email);
}
