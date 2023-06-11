package fr.eitelalaric.gestiondestock.handlers;

import fr.eitelalaric.gestiondestock.exception.EntityNotFoundException;
import fr.eitelalaric.gestiondestock.exception.ErrorCodes;
import fr.eitelalaric.gestiondestock.exception.InvalidEntityException;
import fr.eitelalaric.gestiondestock.exception.InvalidOperationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Collections;

@RestControllerAdvice
public class RestExceptionhandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorDto> handleException(EntityNotFoundException exception, WebRequest webRequest) {
        return new ResponseEntity<>(
                ErrorDto.builder()
                        .code(exception.getErrorCodes())
                        .httpCode(HttpStatus.NOT_FOUND.value())
                        .message(exception.getMessage())
                        .build(),
                HttpStatus.NOT_FOUND
        );
    }
    @ExceptionHandler(InvalidEntityException.class)
    public ResponseEntity<ErrorDto> handleException(InvalidEntityException exception, WebRequest webRequest) {
        return new ResponseEntity<>(
                ErrorDto.builder()
                        .code(exception.getErrorCodes())
                        .httpCode(HttpStatus.BAD_REQUEST.value())
                        .message(exception.getMessage())
                        .errors(exception.getErrors())
                        .build(),
                HttpStatus.BAD_REQUEST
        );
    }
 @ExceptionHandler(InvalidOperationException.class)
    public ResponseEntity<ErrorDto> handleException(InvalidOperationException exception, WebRequest webRequest) {
        return new ResponseEntity<>(
                ErrorDto.builder()
                        .code(exception.getErrorCodes())
                        .httpCode(HttpStatus.NOT_FOUND.value())
                        .message(exception.getMessage())
                        .build(),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorDto> handleException(BadCredentialsException exception, WebRequest webRequest) {
        return new ResponseEntity<>(
                ErrorDto.builder()
                        .code(ErrorCodes.BAD_CREDENTIALS)
                        .httpCode(HttpStatus.UNAUTHORIZED.value())
                        .message(exception.getMessage())
                        .errors(Collections.singletonList("username or / and password aren't valid"))
                        .build(),
                HttpStatus.UNAUTHORIZED
        );

    }

}