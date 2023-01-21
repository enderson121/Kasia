package com.blog.app.task.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommentPostUserDTO {

    private Integer idPost;
    private Integer idUser;
    private String body;
}
