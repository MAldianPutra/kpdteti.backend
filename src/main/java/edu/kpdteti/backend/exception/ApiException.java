package edu.kpdteti.backend.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
