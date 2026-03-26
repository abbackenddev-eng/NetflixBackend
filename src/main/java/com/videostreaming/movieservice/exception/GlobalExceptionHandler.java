package com.videostreaming.movieservice.exception;

import com.videostreaming.movieservice.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResponse<?> handleResourceNotFound(ResourceNotFoundException ex){
        return new ApiResponse<>(
                false,
                ex.getMessage(),
                null
                );
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResponse<?> handleGenericException(Exception ex){
        return new ApiResponse<>(
                false,
                "Something went wrong",
                null
        );

    }
}
