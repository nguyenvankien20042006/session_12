package com.example.bai2.exception;

import com.example.bai2.model.ApiDataResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiDataResponse<String>> handleException(RuntimeException ex) {
        log.error("Đã có lỗi xảy ra: {}", ex.getMessage());
        return new ResponseEntity<>(new ApiDataResponse<>(false, ex.getMessage(), null, HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }
}
