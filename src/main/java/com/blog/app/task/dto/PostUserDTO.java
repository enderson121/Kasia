package com.blog.app.task.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostUserDTO {
    private Integer idPost;
    private Integer views;
    private String title;
    private String body;
    private String nameUser;
    private String emailUser;
}
