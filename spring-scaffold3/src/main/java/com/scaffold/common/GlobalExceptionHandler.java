package com.scaffold.common;

import com.scaffold.api.model.ApiResponse;
import com.scaffold.common.exceptions.CustomException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.rmi.ServerException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ServerException.class)
    public ResponseEntity<ApiResponse> handleException(final ServerException err) {
        return ResponseEntity
                .internalServerError()
                .body(ApiResponse.error(err.getMessage()));
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> handleException(final MethodArgumentNotValidException err) {
        return ResponseEntity
                .badRequest()
                .body(ApiResponse.error(err.getMessage()));
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ApiResponse> handleException(final MissingServletRequestParameterException err) {
        return ResponseEntity
                .badRequest()
                .body(ApiResponse.error(err.getMessage()));
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<ApiResponse> handleException(final EmptyResultDataAccessException err) {
        return ResponseEntity
                .badRequest()
                .body(ApiResponse.error(err.getMessage()));
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<String> handleCustomException(CustomException ex) {
        return ResponseEntity.status(ex.getStatus()).body(ex.getMessage());
    }

}
