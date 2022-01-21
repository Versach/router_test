package com.testcompany.router.rest.advices;

import com.testcompany.router.rest.exceptions.NoRouteException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class NoRouteAdvice {
    @ResponseBody
    @ExceptionHandler(NoRouteException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String noRouteHandler(NoRouteException ex) {
        return ex.getMessage();
    }

}
