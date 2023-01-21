package com.blog.app.task.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessageResponse {
    private int statusCode;
    private Date timestamp;
    private String message;
    private String requestUri;
    private String paramsFrom;
    private String paramsTo;
}
