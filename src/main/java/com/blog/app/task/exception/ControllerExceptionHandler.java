package com.blog.app.task.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(MessageException.class)
    public ResponseEntity<ErrorMessageResponse> messageException(MessageException ex, WebRequest request, HttpServletRequest httpservletrequest){
        ErrorMessageResponse errorMessageResponse = new ErrorMessageResponse(
                HttpStatus.NOT_FOUND.value(),
                new Date(),
                ex.getMessage(),
                httpservletrequest.getRequestURI(),
                request.getParameter("from"),
                request.getParameter("to")
        );
        return new ResponseEntity<ErrorMessageResponse>(errorMessageResponse,HttpStatus.NOT_FOUND);
    }

}
