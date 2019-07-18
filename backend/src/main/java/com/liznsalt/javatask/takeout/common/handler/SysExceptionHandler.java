package com.liznsalt.javatask.takeout.common.handler;

import com.liznsalt.javatask.takeout.common.exception.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

//@ControllerAdvice
public class SysExceptionHandler {
//    @ExceptionHandler(UnauthorizedException.class)
//    @ResponseBody
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> handleUserNotExistsException(UnauthorizedException e) {
        Map<String, Object> map = new HashMap<>();
        map.put("message", e.getMessage());
        return map;
    }
}
