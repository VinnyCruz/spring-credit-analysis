package org.jazztech.creditanalysis.exceptionhandler;

import java.net.URI;
import java.time.LocalDateTime;
import org.jazztech.creditanalysis.exception.ClientNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ControlExceptionHandler {
    public static final String TIMESTAMP = "timestamp";

    @ExceptionHandler
    public ProblemDetail clientNotFoundExceptionHandler(ClientNotFoundException e) {
        final ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setType(URI.create(""));
        problemDetail.setProperty(TIMESTAMP, LocalDateTime.now());
        problemDetail.setDetail(e.getMessage());
        return problemDetail;
    }
}
