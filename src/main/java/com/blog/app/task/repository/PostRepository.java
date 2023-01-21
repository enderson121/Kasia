package com.blog.app.task.repository;

import com.blog.app.task.model.Post;
import com.blog.app.task.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer>, PagingAndSortingRepository<Post,Integer> {

    //QueryMethod
    Post findTop1ById(Integer idPost);

    Page<Post> findAllByUserId(Integer idUser, Pageable pageable);

    @Query(value = "SELECT posts.id as id," +
            "views as view, " +
            "title as title, " +
            "body as body, " +
            "posts.created_at as createdAt, " +
            "posts.updated_at as updatedAt, " +
            "users.* as user " +
            "FROM posts INNER JOIN users ON posts.user_id = users.id where posts.user_id = :userId",
            nativeQuery = true)
    List<Post> findUserByUserIdPost(@Param("userId") int userId);

}
