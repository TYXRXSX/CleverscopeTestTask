package ru.clevertec.cleverscope.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.clevertec.cleverscope.exception.ErrorResponse;
import ru.clevertec.cleverscope.exception.ResourceAlreadyExistsException;
import ru.clevertec.cleverscope.exception.ResourceNotFoundException;

import java.rmi.ServerException;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;

@Slf4j
@RestControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
        log.error("Resource not found exception: {}", ex.getMessage(), ex);
        ErrorResponse errorResponse = new ErrorResponse(NOT_FOUND.toString(), ex.getMessage());
        return new ResponseEntity<>(errorResponse, NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        log.error("Unknown exception: {}", ex.getMessage(), ex);

        ErrorResponse errorResponse = new ErrorResponse(INTERNAL_SERVER_ERROR.toString(), ex.getMessage());
        return new ResponseEntity<>(errorResponse, INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceAlreadyExistsException ex) {
        log.error("Resource already exists exception: {}", ex.getMessage(), ex);

        ErrorResponse errorResponse = new ErrorResponse(CONFLICT.toString(), ex.getMessage());
        return new ResponseEntity<>(errorResponse, CONFLICT);
    }

}
