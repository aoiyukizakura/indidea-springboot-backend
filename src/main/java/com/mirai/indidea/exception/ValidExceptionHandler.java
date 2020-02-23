package com.mirai.indidea.exception;

import com.mirai.indidea.dto.Result.ResultDto;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ValidExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    public ResultDto handleRRException(RuntimeException e) {
        return null;
    }
}
