package ru.clevertec.cleverscope.handler;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import ru.clevertec.cleverscope.exception.ErrorResponse;
import ru.clevertec.cleverscope.exception.ResourceAlreadyExistsException;
import ru.clevertec.cleverscope.exception.ResourceNotFoundException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ExceptionHandlerAdviceTest {

    @Test
    public void testHandleResourceNotFoundException() {
        ResourceNotFoundException exception = new ResourceNotFoundException("Resource not found");
        ExceptionHandlerAdvice advice = new ExceptionHandlerAdvice();

        ResponseEntity<ErrorResponse> response = advice.handleResourceNotFoundException(exception);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Resource not found", response.getBody().message());
    }

    @Test
    public void testHandleException() {
        Exception exception = new Exception("Unknown exception");
        ExceptionHandlerAdvice advice = new ExceptionHandlerAdvice();

        ResponseEntity<ErrorResponse> response = advice.handleException(exception);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Unknown exception", response.getBody().message());
    }

    @Test
    public void testHandleResourceNotFoundException1() {
        ResourceAlreadyExistsException exception = new ResourceAlreadyExistsException("Resource already exists");
        ExceptionHandlerAdvice advice = new ExceptionHandlerAdvice();

        ResponseEntity<ErrorResponse> response = advice.handleResourceNotFoundException(exception);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("Resource already exists", response.getBody().message());
    }
}