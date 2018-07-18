/*package com.donglam.webhoconline.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.context.config.ResourceNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler({Exception.class,NoHandlerFoundException.class,
    	IllegalArgumentException.class,ResourceNotFoundException.class,Throwable.class})
    public String handleError(HttpServletRequest request, Exception e)   {
        Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Request: " + request.getRequestURL() + " raised " + e);
        return "error";
    }	
}*/