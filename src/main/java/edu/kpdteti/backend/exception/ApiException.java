package edu.kpdteti.backend.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@Data
public class ApiException {
    private String timestamp;
    private HttpStatus status;
    private String message;
    private String path;

    public ApiException(HttpStatus status, String message, WebRequest request) {
        this.timestamp = LocalDateTime.now().toString();
        this.status = status;
        this.message = message;
        this.path = ((ServletWebRequest) request).getRequest().getRequestURI();
    }
}
