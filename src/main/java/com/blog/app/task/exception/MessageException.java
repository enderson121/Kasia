package com.blog.app.task.exception;

public class MessageException extends Exception {

    private static final long serialVersionUID = 1L;

    public MessageException(String errorMessage){
        super(errorMessage);
    }
}
