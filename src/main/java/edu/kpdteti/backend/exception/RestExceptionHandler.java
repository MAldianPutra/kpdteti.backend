package edu.kpdteti.backend.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {EntityNotFoundException.class})
    protected ResponseEntity<Object> handleNotFound(
            RuntimeException ex, WebRequest request) {

        ApiException apiException = new ApiException(HttpStatus.NOT_FOUND,
                ex.getMessage(), request);

        return handleExceptionInternal(ex, apiException,
                new HttpHeaders(), apiException.getStatus(), request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid
            (MethodArgumentNotValidException ex,
             HttpHeaders headers, HttpStatus status, WebRequest request) {

        ApiException apiException = new ApiException(HttpStatus.BAD_REQUEST,
                ex.getMessage(), request);

        return handleExceptionInternal(ex, apiException,
                new HttpHeaders(), apiException.getStatus(), request);
    }

}
