package br.com.matheus.dsclients.controller.exceptions;

import br.com.matheus.dsclients.services.exceptions.ClientNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {

    HttpStatus status = HttpStatus.I_AM_A_TEAPOT;

    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<StandardError> entityNotFound(ClientNotFoundException e, HttpServletRequest request) {

        status = HttpStatus.NOT_FOUND;

        var error = new StandardError();
        error.setTimestamp(Instant.now());
        error.setStatus(status.value());
        error.setError("Resource not found!");
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());
        return ResponseEntity.status(status).body(error);
    }
}
